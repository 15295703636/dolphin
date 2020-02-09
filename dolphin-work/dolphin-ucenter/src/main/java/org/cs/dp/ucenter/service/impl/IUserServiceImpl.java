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
import org.cs.dolphin.common.utils.Constants;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dolphin.common.utils.MD5Util;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.ucenter.common.Constant;
import org.cs.dp.ucenter.common.RedisUtil;
import org.cs.dp.ucenter.common.SpringUtils;
import org.cs.dp.ucenter.common.UploadUserListener;
import org.cs.dp.ucenter.domain.*;
import org.cs.dp.ucenter.domain.entity.UserEntity;
import org.cs.dp.ucenter.domain.entity.UserOrgEntity;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.mapper.UserOrgMapper;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        if (!user.getUser_pwd().equals(param.getPassWord())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PWD_ERROR_MSG);
        }
        //用户名密码校验通过，根据用户名生成token，存入redis，并返回调用端
        String token = MD5Util.MD5(param.getUserName() + DateUtil.getCurrentDate(Constants.DATE_PATTERN));

        try {
            redisUtil.set(RedisConstant.USER_TOKEN_PATH + token, JSON.toJSONString(user), RedisConstant.USER_TOKEN_EXPIRED_TIME);
        } catch (Exception e) {
            throw new BaseException(null, Constant.EXCEPTION_MSG);
        }
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
        String token = request.getHeader("token");
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
        UserEntity userEntity = userMapper.selectByPrimaryKey(param.getUser_id());
        if (null == userEntity) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_NO_EXIST_MSG);
        }
        if (null == ThreadLocalUserInfoUtil.get().getUser_type()) {
            if (StringUtils.isEmpty(param.getUser_pwd())) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PWD_NULL_MSG);
            }
            if (!param.getUser_id().equals(userEntity.getUser_pwd())) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PWD_ERROR_MSG);
            }
        }
        userEntity.setUser_pwd(param.getNew_pwd());
        int res = userMapper.updateByPrimaryKeySelective(userEntity);
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
    public ReturnInfo add(AddUserBean record, boolean isAuto) {
        if (null != userMapper.selectByUserName(record.getUser_name())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_EXIST_MSG);
        }
        //添加用户
        userMapper.insertSelective(record);
        if (!isAuto) {
            //用户组织关系维护
            userOrgMapper.insertSelective(new UserOrgEntity(null, record.getOrg_id(), record.getUser_id()));
        }
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo del(List<Integer> userIds) {
        if (0 == userIds.size()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "请选择用户!");
        }
        userMapper.deleteByPrimaryKey(userIds);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo edit(UserEntity record) {
        userMapper.updateByPrimaryKeySelective(record);
        return new ReturnInfo();
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
