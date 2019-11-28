package org.cs.dp.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.base.UserInfo;
import org.cs.dolphin.common.constant.RedisConstant;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.*;
import org.cs.dp.ucenter.common.Constant;
import org.cs.dp.ucenter.common.RedisUtil;
import org.cs.dp.ucenter.domain.OrgIdAndTokenBean;
import org.cs.dp.ucenter.domain.ResetPwdBean;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.UserEntity;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("ALL")
@Slf4j
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
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

        boolean result = redisUtil.set(RedisConstant.USER_TOKEN_PATH + token, JSON.toJSONString(user), RedisConstant.USER_TOKEN_EXPIRED_TIME);
        if (!result) {
            return new ReturnInfo(MessageCode.DB_CONNECTION_EXCEPTION, Constant.EXCEPTION_MSG);
        }
        return new ReturnInfo(token);
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
        UserEntity userEntity = userMapper.selectByPrimaryKey(param.getUserId());
        if (null == userEntity) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_NO_EXIST_MSG);
        }
        if (!param.getOldPwd().equals(userEntity.getUser_pwd())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PWD_ERROR_MSG);
        }
        userEntity.setUser_pwd(param.getNewPwd());
        int res = userMapper.updateByPrimaryKeySelective(userEntity);
        return new ReturnInfo();
    }


    /**
     * 添加用户的同时，需不需要添加组织和用户的关系
     *
     * @param record
     * @return
     */
    @Override
    public ReturnInfo add(UserEntity record) {
        if (null != userMapper.selectByUserName(record.getUser_name())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_EXIST_MSG);
        }
        userMapper.insertSelective(record);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo del(Integer user_id) {
        userMapper.deleteByPrimaryKey(user_id);
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

}
