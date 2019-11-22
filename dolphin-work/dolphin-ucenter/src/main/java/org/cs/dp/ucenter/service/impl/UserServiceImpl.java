package org.cs.dp.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.*;
import org.cs.dp.ucenter.common.Constant;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.UserEntity;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 1.先登录：根据用户名密码查询用户信息
     * 2.登录成功后存入redis
     *
     * @param param
     * @return
     */
    @Override
    public ReturnInfo login(UPBean param) {
        if (StringUtil.isNull(param.getPassWord()) || StringUtil.isNull(param.getUserName())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_PWD_MSG);
        }
        //根据用户名查询用户信息，判断用户是否存在
        UserEntity user = userMapper.selectByUserName(param.getUserName());
        if (null == user) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.NAME_MSG);
        }
        //判断用户名密码是否正确
        if (!user.getUser_pwd().equals(param.getPassWord())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.PWD_MSG);
        }
        //用户名密码校验通过，根据用户名生成token，存入redis，并返回调用端
        String token = MD5Util.MD5(param.getUserName() + DateUtil.getCurrentDate(Constants.DATE_PATTERN));
        boolean result = RedisUtil.set(RedisUtil.USER_TOKEN_PATH + token, JSON.toJSONString(user), RedisUtil.USER_TOKEN_EXPIRED_TIME);
        if (!result) {
            return new ReturnInfo(MessageCode.DB_CONNECTION_EXCEPTION, Constant.EXCEPTION_MSG);
        }
        return new ReturnInfo(token);
    }

    @Override
    public ReturnInfo loginOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        RedisUtil.remove(RedisUtil.USER_TOKEN_PATH + token);
        return new ReturnInfo();
    }

    /**
     * 添加用户的同时，需不需要添加组织和用户的关系
     * @param record
     * @return
     */
    @Override
    public ReturnInfo add(UserEntity record) {
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
    public List getList() {
        return null;
    }

    @Override
    public PageInfo selectUserByOrg(SplitPageInfo page, Map map) {
        if (page != null) {
            PageHelper.startPage(page.getCurrPage(), page.getPerPageNum());
        }
        List list = userMapper.getList();
        PageInfo p = new PageInfo(list);
        page.setTotals((int) p.getTotal());
        log.info("selectUserByOrg end");
        return p;
    }

}
