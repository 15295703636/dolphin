package org.cs.dp.ucenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public List getList() {
        return userMapper.getList();
    }

    @Override
    public PageInfo selectUserByOrg(SplitPageInfo page, Map map) {
        if (page !=null){
            PageHelper.startPage(page.getCurrPage(),page.getPerPageNum());
        }
        List list = userMapper.getList();
        PageInfo p =new PageInfo(list);
        page.setTotals((int)p.getTotal());
        logger.info("selectUserByOrg end");
        return p;
    }
}
