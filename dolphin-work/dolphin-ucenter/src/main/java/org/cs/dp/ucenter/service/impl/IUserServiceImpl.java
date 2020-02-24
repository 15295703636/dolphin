package org.cs.dp.ucenter.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.base.UserInfo;
import org.cs.dolphin.common.constant.RedisConstant;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.*;
import org.cs.dp.radar.api.entity.RestOrgUserReq;
import org.cs.dp.radar.api.entity.RestUser;
import org.cs.dp.ucenter.common.Constant;
import org.cs.dp.ucenter.common.RedisUtil;
import org.cs.dp.ucenter.common.SpringUtils;
import org.cs.dp.ucenter.common.UploadUserListener;
import org.cs.dp.ucenter.domain.*;
import org.cs.dp.ucenter.domain.entity.UserEntity;
import org.cs.dp.ucenter.domain.entity.UserOrgEntity;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.mapper.UserOrgMapper;
import org.cs.dp.ucenter.service.ISoMruService;
import org.cs.dp.ucenter.service.ISuperUserService;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Slf4j
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserOrgMapper userOrgMapper;
    @Autowired
    private ISuperUserService iSuperUserService;
    @Autowired
    private ISoMruService iSoMruService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 1.先登录：根据用户名密码查询用户信息
     * 2.登录成功后存入redis
     *
     * @param param
     * @return
     */
    @Override
    public ReturnInfo login(UPBean param) throws BaseException {
        //根据用户名查询用户信息，判断用户是否存在
        UserInfo user = userMapper.selectByUserName(param.getUserName());
        if (null == user) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_NO_EXIST_MSG);
        }
        //判断用户名密码是否正确
        if (!MD5Util.MD5(user.getUser_pwd()).equals(param.getPassWord())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PWD_ERROR_MSG);
        }
        //用户名密码校验通过，根据用户名生成token，存入redis，并返回调用端
        String token = MD5Util.MD5(param.getUserName() + DateUtil.getCurrentDate(Constants.DATE_PATTERN));

        try {
            redisUtil.set(RedisConstant.USER_TOKEN_PATH + token, JSON.toJSONString(user), RedisConstant.USER_TOKEN_EXPIRED_TIME);
        } catch (Exception e) {
            throw new BaseException(null, Constant.EXCEPTION_MSG);
        }
        /*ReturnInfo returnInfo = iSuperUserService.getSuperUserByUserId(user.getUser_id());
        if(MessageCode.COMMON_SUCCEED_FLAG == returnInfo.getReturnCode()){
            return returnInfo;
        }*/
        user.setUser_pwd(null);
        Map userInfo = new HashMap();
        userInfo.put("token", token);
        userInfo.put("userInfo", user);
        return new ReturnInfo(userInfo);
    }

    @Override
    public ReturnInfo getUserInfo() {
        return new ReturnInfo<UserInfo>(ThreadLocalUserInfoUtil.get());
    }

    @Override
    public ReturnInfo loginOut(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (null == token) {
            token = request.getHeader("token");
        }
        redisUtil.remove(RedisConstant.USER_TOKEN_PATH + token);
        return new ReturnInfo();
    }

    /**
     * 1.判断用户是否存在
     * 2.判断当前密码是否正确
     * 3.更新新密码
     *
     * @param param
     * @return
     */
    @Override
    public ReturnInfo resetPwd(ResetPwdBean param) {
        List<UserEntity> userEntity = userMapper.selectByPrimaryKey(Arrays.asList(param.getUser_id()));
        if (0 == userEntity.size()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_NO_EXIST_MSG);
        }
        if (null == ThreadLocalUserInfoUtil.get().getUser_type()) {
            if (StringUtils.isEmpty(param.getUser_pwd())) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PWD_NULL_MSG);
            }
            if (!param.getUser_pwd().equals(MD5Util.MD5(userEntity.get(0).getUser_pwd()))) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PWD_ERROR_MSG);
            }
        }
        userEntity.get(0).setUser_pwd(param.getNew_pwd());
        int res = userMapper.updateByPrimaryKeySelective(userEntity.get(0));
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo checkUserInfo(String userName, Integer id) {
        UserEntity userEntity = userMapper.checkUserInfo(id, userName);
        if (null != userEntity) {
            if (!StringUtils.isEmpty(userEntity.getUser_name()) && userEntity.getUser_name().equals(userName)) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_EXIST_MSG);
            }
        }
        return new ReturnInfo();
    }


    /**
     * 添加用户的同时，需不需要添加组织和用户的关系
     * *
     *
     * @param record
     * @param isAuto 是添加租户自动添加，还是租户手动添加标志
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo add(AddUserBean record, boolean isAuto) throws BaseException {
        if (null != userMapper.selectByUserName(record.getUser_name())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_EXIST_MSG);
        }
        //添加用户
        userMapper.insertSelective(record);
        if (!isAuto) {
            //用户组织关系维护
            userOrgMapper.insertSelective(new UserOrgEntity(null, record.getOrg_id(), record.getUser_id()));
        }
        try {
            if (!addYSX(record)) {
                throw new BaseException("业务异常", "调用云视讯服务异常");
            }
        } catch (Exception e) {
            log.error("调用云视讯服务异常", e);
            throw new BaseException(e.getMessage(), "调用云视讯服务异常");
        }
        return new ReturnInfo();
    }

    private boolean addYSX(AddUserBean record) throws NoSuchAlgorithmException {
        boolean result = false;
        RestOrgUserReq restOrgUserReq = new RestOrgUserReq();
        restOrgUserReq.setName(record.getUser_name());
        restOrgUserReq.setPassword(SHA1Util.sha1(record.getUser_pwd()));
        UserInfo userInfo = ThreadLocalUserInfoUtil.get();
        //添加租户管理员
        if (null != userInfo.getUser_type()) {
            restOrgUserReq.setCellphone("18211111119");
            restOrgUserReq.setDisplayName("testZh");
            restOrgUserReq.setEmail("testAdmin9@qq.com");
            restOrgUserReq.setRole("USER");//TODO 租户管理员是什么角色
        } else {
            restOrgUserReq.setCellphone(record.getUser_tel());
            restOrgUserReq.setDisplayName(record.getUser_qname());
            restOrgUserReq.setEmail(record.getUser_email());
            restOrgUserReq.setRole("USER");
        }
        restOrgUserReq.setDescription("testZh");
        //restOrgUserReq.setDeptId(30);
        ReturnInfo res = iSoMruService.getService(iSoMruService.ADDUSER_NAME, record.getCustomer_id(), restOrgUserReq);

        if (MessageCode.COMMON_SUCCEED_FLAG == res.getReturnCode()) {
            RestUser restUser = (RestUser) res.getReturnData();
            UserEntity userEntity = new UserEntity();
            userEntity.setYsx_id(restUser.getId());
            userEntity.setUser_id(record.getUser_id());
            userMapper.updateByPrimaryKeySelective(userEntity);
            result = true;
        }
        return result;
    }

    @Override
    public ReturnInfo del(List<Integer> userIds) {
        if (0 == userIds.size()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "请选择用户!");
        }

        if (dealDel(userIds).length() > 0) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "调用云视讯接口异常!");
        }

        userMapper.deleteByPrimaryKey(userIds);
        return new ReturnInfo();
    }

    //删除云视讯用户
    private String dealDel(List<Integer> userIds) {
        List<UserEntity> users = userMapper.selectByPrimaryKey(userIds);
        String result = "";
        users.forEach(e -> {
            ReturnInfo returnInfo = iSoMruService.getService(iSoMruService.DELETEUSER_NAME, null, e.getYsx_id());
            if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
                result.concat("false");
                return;
            }
        });
        return result;
    }

    @Override
    public ReturnInfo edit(UserEntity record) {
        dealEdit(record);
        userMapper.updateByPrimaryKeySelective(record);
        return new ReturnInfo();
    }

    //更新云视讯用户
    private void dealEdit(UserEntity record) {
        SoMruUpUserReqBean soMruUpUserReq = new SoMruUpUserReqBean();
        soMruUpUserReq.setId(userMapper.selectByPrimaryKey(Arrays.asList(record.getUser_id())).get(0).getYsx_id());
        soMruUpUserReq.setCellphone(record.getUser_tel());
        soMruUpUserReq.setEmail(record.getUser_email());
        soMruUpUserReq.setPassword(record.getUser_pwd());
        soMruUpUserReq.setDisplayName(record.getUser_qname());
        soMruUpUserReq.setName(record.getUser_name());
        soMruUpUserReq.setRole("USER");
        soMruUpUserReq.setStatus(0);

        iSoMruService.getService(iSoMruService.UPDATEUSER_NAME, null, soMruUpUserReq);
    }

    @Override
    public ReturnInfo getUsersByOrgId(RequestPage<SplitPageInfo, OrgIdAndTokenBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<UserEntity> userEntities = userMapper.getListByOrgId(param.getInfo().getOrg_id(), ThreadLocalUserInfoUtil.get().getUser_id());
        PageInfo p = new PageInfo(userEntities);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, userEntities);
    }

    @Override
    public ReturnInfo getUsersList(RequestPage<SplitPageInfo, GetUserReqBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        GetUserReqBean getUserReqBean = param.getInfo();
        getUserReqBean.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<GetUserListResBean> userEntities = userMapper.getUsersList(getUserReqBean);
        PageInfo p = new PageInfo(userEntities);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, userEntities);
    }


    @Override
    public List getList() {
        return null;
    }

    @Override
    public ReturnInfo upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), AddUserBean.class,
                new UploadUserListener(SpringUtils.getBean(IUserServiceImpl.class))).sheet().doRead();
        return new ReturnInfo();
    }

}
