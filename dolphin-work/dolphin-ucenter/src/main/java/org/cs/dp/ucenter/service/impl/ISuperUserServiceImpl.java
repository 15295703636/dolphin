package org.cs.dp.ucenter.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
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
import org.cs.dp.ucenter.common.UploadDataListener;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String token = request.getHeader("token");
        redisUtil.remove(RedisConstant.USER_TOKEN_PATH + token);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo checkAddInfo(CheckAddInfoReqBean record) {
        CheckAddInfoReqBean checkRes = superUserMapper.checkAddInfo(record);
        if (null != checkRes) {
            if (!StringUtils.isEmpty(record.getUser_name()) && record.getUser_name().equals(checkRes.getUser_name())) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_EXIST_MSG);
            }
            if (!StringUtils.isEmpty(record.getUser_tel()) && record.getUser_tel().equals(checkRes.getUser_tel())) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PHONE_EXIST_MSG);
            }
        }
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo add(SuperUserEntity record) {
        if (0 < superUserMapper.selectByUserNameCou(record.getUser_name())) {
            log.error("用户已存在：{}", JSON.toJSONString(record));
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_EXIST_MSG);
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

    @Override
    public ReturnInfo upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), SuperUserEntity.class,
                new UploadDataListener(SpringUtils.getBean(ISuperUserService.class))).sheet().doRead();
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo resetPwd(ResetSuperPwdBean param) {
        SuperUserEntity superUserEntity = superUserMapper.selectByUserId(param.getUser_id());
        if(!param.getUser_id().equals(ThreadLocalUserInfoUtil.get().getUser_id())){
            if (!param.getUser_pwd().equals(superUserEntity.getUser_pwd())) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NOW_PWD_ERROR_MSG);
            }
        }
        SuperUserEntity updateInfo = new SuperUserEntity();
        updateInfo.setUser_id(param.getUser_id());
        updateInfo.setUser_pwd(param.getNew_pwd());
        superUserMapper.updateByPrimaryKeySelective(updateInfo);
        return new ReturnInfo();
    }

}
