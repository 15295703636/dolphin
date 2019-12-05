package org.cs.dp.ucenter.service.impl;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.constant.RedisConstant;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.*;
import org.cs.dp.ucenter.common.Constant;
import org.cs.dp.ucenter.common.RedisUtil;
import org.cs.dp.ucenter.common.SpringUtils;
import org.cs.dp.ucenter.common.UploadDataListener;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;
import org.cs.dp.ucenter.mapper.SuperUserMapper;
import org.cs.dp.ucenter.service.ISuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public ReturnInfo login(UPBean param) {
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
        boolean result = redisUtil.set(RedisConstant.USER_TOKEN_PATH + token, JSON.toJSONString(user), RedisConstant.USER_TOKEN_EXPIRED_TIME);
        if (!result) {
            return new ReturnInfo(MessageCode.DB_CONNECTION_EXCEPTION, Constant.EXCEPTION_MSG);
        }
        return new ReturnInfo(token);
    }

    @Override
    public ReturnInfo loginOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        redisUtil.remove(RedisConstant.USER_TOKEN_PATH + token);
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
    public ReturnInfo del(Integer user_id) {
        superUserMapper.deleteByPrimaryKey(user_id);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo edit(SuperUserEntity record) {
        record.setUser_name(null);
        record.setCreate_time(null);
        superUserMapper.updateByPrimaryKeySelective(record);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getManage() {
        return new ReturnInfo(superUserMapper.selectManage());
    }

    @Override
    public ReturnInfo upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), SuperUserEntity.class,
                new UploadDataListener(SpringUtils.getBean(ISuperUserService.class))).sheet().doRead();
        return new ReturnInfo();
    }

}
