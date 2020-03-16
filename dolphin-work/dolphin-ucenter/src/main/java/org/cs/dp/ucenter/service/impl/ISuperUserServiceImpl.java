package org.cs.dp.ucenter.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import org.cs.dp.ucenter.common.UploadSuperUserListener;
import org.cs.dp.ucenter.domain.*;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;
import org.cs.dp.ucenter.domain.entity.UserEntity;
import org.cs.dp.ucenter.mapper.SuperUserMapper;
import org.cs.dp.ucenter.service.ISuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("ALL")
@Slf4j
@Service
public class ISuperUserServiceImpl implements ISuperUserService {

    @Autowired
    private SuperUserMapper superUserMapper;
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
        SuperUserEntity user = superUserMapper.selectByUserName(param.getUserName());
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
            UserInfo userInfo = JSONObject.parseObject(JSON.toJSONString(user), UserInfo.class);
            redisUtil.set(RedisConstant.USER_TOKEN_PATH + token, JSON.toJSONString(userInfo), RedisConstant.USER_TOKEN_EXPIRED_TIME);
        } catch (Exception e) {
            log.error("token，存入redis异常", e);
            throw new BaseException(null, Constant.EXCEPTION_MSG);
        }
        user.setUser_pwd(null);
        Map userInfo = new HashMap();
        userInfo.put("token", token);
        userInfo.put("userInfo", user);
        return new ReturnInfo(userInfo);
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

    public ReturnInfo checkUserInfo(CheckAddInfoReqBean record) {
        List<CheckAddInfoReqBean> checkRes = superUserMapper.checkAddInfo(record);
        if (0 < checkRes.size()) {
            for (CheckAddInfoReqBean user : checkRes) {
                //不等于null是修改
                if (!Objects.equals(record.getUser_id(), user.getUser_id())) {
                    if (Objects.equals(user.getUser_name(), record.getUser_name())) {
                        return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_EXIST_MSG);
                    } else if (Objects.equals(user.getUser_email(), record.getUser_email())) {
                        return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.EMAIL_EXIST_MSG);
                    } else if (Objects.equals(user.getUser_tel(), record.getUser_tel())) {
                        return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PHONE_EXIST_MSG);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ReturnInfo checkAddInfo(CheckAddInfoReqBean record) {
        ReturnInfo returnInfo = checkUserInfo(record);
        if (null != returnInfo) {
            return returnInfo;
        }
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo add(SuperUserEntity record) {
        ReturnInfo returnInfo = checkInfo(record);
        if (null != returnInfo) {
            log.error("用户已存在：{}", JSON.toJSONString(record));
            return returnInfo;
        }
        superUserMapper.insertSelective(record);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo del(List<Integer> user_ids) {
        if (user_ids.size() == 0) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "请选择删除信息!");
        }
        superUserMapper.deleteByPrimaryKey(user_ids);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo edit(SuperUserEntity record) {
        ReturnInfo returnInfo = checkInfo(record);
        if (null != returnInfo) {
            log.error("用户已存在：{}", JSON.toJSONString(record));
            return returnInfo;
        }
        //如果是超级管理员校验当前密码
        if (2 == record.getUser_type()) {
            SuperUserEntity user = superUserMapper.selectManage(record.getUser_id(), null).get(0);
            if (null != record.getUser_pwd() && !user.getUser_pwd().equals(record.getUser_pwd())) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NOW_PWD_ERROR_MSG);
            }
            record.setUser_pwd(record.getNew_pwd());
        }
        record.setUser_name(null);
        record.setCreate_time(null);
        superUserMapper.updateByPrimaryKeySelective(record);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getManage(RequestPage<SplitPageInfo, SuperUserGetReqBean> param) {
        if (null != param.getPage()) {
            PageHelper.startPage(param.getPage().getCurrPage(), param.getPage().getPerPageNum());
        }
        List<SuperUserEntity> list = superUserMapper.selectManage(param.getInfo().getUser_id(), param.getInfo().getUser_name());
        PageInfo p = new PageInfo(list);
        param.getPage().setTotals((int) p.getTotal());
        return new ReturnInfo(param.getPage(), list);
    }


    private ReturnInfo checkInfo(SuperUserEntity record) {
        List<CheckAddInfoReqBean> userInfos = superUserMapper.checkAddInfo(JSONObject.parseObject(JSON.toJSONString(record), CheckAddInfoReqBean.class));
        if (userInfos.size() > 0) {
            for (CheckAddInfoReqBean user : userInfos) {
                //不等于null是修改
                if (!Objects.equals(record.getUser_id(), user.getUser_id())) {
                    if (Objects.equals(user.getUser_name(), record.getUser_name())) {
                        return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_EXIST_MSG);
                    } else if (Objects.equals(user.getUser_email(), record.getUser_email())) {
                        return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.EMAIL_EXIST_MSG);
                    } else if (Objects.equals(user.getUser_tel(), record.getUser_tel())) {
                        return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PHONE_EXIST_MSG);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ReturnInfo upload(MultipartFile file) throws IOException {
        UploadSuperUserListener uploadSuperUser = new UploadSuperUserListener();
        EasyExcel.read(file.getInputStream(), SuperUserEntity.class, uploadSuperUser).sheet().doRead();
        List<SuperUserEntity> superUserList = uploadSuperUser.getSuperUserList();
        ReturnInfo returnInfo = null;
        List<String> errorInfo = new ArrayList<>();
        for (SuperUserEntity addUser : superUserList) {
            returnInfo = checkInfo(addUser);
            if (null != returnInfo) {
                errorInfo.add(String.format("租户:%s 添加失败;原因：%s", addUser.getUser_name(), returnInfo.getMsg()));
            } else {
                add(addUser);
            }
        }
        if (errorInfo.size() > 0) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, JSON.toJSONString(errorInfo));
        }
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo resetPwd(ResetSuperPwdBean param) {
        SuperUserEntity superUserEntity = superUserMapper.selectByUserId(ThreadLocalUserInfoUtil.get().getUser_id());
        if (!param.getUser_pwd().equals(superUserEntity.getUser_pwd())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NOW_PWD_ERROR_MSG);
        }
        SuperUserEntity updateInfo = new SuperUserEntity();
        updateInfo.setUser_id(param.getUser_id());
        updateInfo.setUser_pwd(param.getNew_pwd());
        superUserMapper.updateByPrimaryKeySelective(updateInfo);
        return new ReturnInfo();
    }

    /**
     * 根据企业用户查询客户代表
     *
     * @param userId
     * @return
     */
    @Override
    public ReturnInfo getSuperUserByCusId() {
        SuperUserEntity superUser = superUserMapper.getSuperUserByCusId(ThreadLocalUserInfoUtil.get().getCustomer_id());
        if (null == superUser) {
            new ReturnInfo(MessageCode.DB_EXCEPTION, Constant.DATA_ERROR_MSG);
        }
        superUser.setUser_pwd(null);
        return new ReturnInfo(superUser);
    }

}
