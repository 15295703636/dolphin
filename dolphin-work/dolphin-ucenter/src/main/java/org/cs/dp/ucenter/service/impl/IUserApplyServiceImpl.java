package org.cs.dp.ucenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.ucenter.domain.entity.UserApplyEntity;
import org.cs.dp.ucenter.mapper.UserApplyMapper;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.service.IUserApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IUserApplyServiceImpl
 * @Description 账号申请实现类
 * @Author Liujt
 * @Date 2019-12-06 03:28:30
 **/
@Slf4j
@Service
public class IUserApplyServiceImpl implements IUserApplyService {
    @Autowired
    private UserApplyMapper userApplyMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnInfo addUserApply(UserApplyEntity param) {
        userApplyMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delUserApply(Integer param) {
        userApplyMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    @Transactional
    public ReturnInfo editUserApply(UserApplyEntity param) {

        //用户是否已存在
        if (1 == param.getApply_result() && null != userMapper.selectByUserName(param.getUser_name())) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "用户已存在!");
        }

        //将数据同步到用户表
        if (1 == param.getApply_result()) {
            int res = userMapper.insertSelectApply(param.getUser_id(), param.getUser_name());
            log.info("同意并添加到用户表结果：{}", res);
        }
        //更新用户结果
        userApplyMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getUserApply(RequestPage<SplitPageInfo, Integer> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<UserApplyEntity> resList = userApplyMapper.selectAll(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}