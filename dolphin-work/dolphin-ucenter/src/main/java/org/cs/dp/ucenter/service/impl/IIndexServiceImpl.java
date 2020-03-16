package org.cs.dp.ucenter.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.ucenter.domain.UserCountBean;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IIndexServiceImpl
 * @Description
 * @Author Liujt
 * @Date 2020/3/6 15:24
 **/
@Service
public class IIndexServiceImpl implements IIndexService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnInfo getUserCount() {
        List<UserCountBean> res = userMapper.getUserCount(ThreadLocalUserInfoUtil.get().getCustomer_id());
        int adminInt = 0;
        int userInt = 0;
        for (UserCountBean e : res) {
            if (4 != e.getRole_id()) {
                adminInt += e.getCou();
            } else {
                userInt += e.getCou();
            }
        }
        Map<String, Integer> resData = new HashMap<>();
        resData.put("admin", adminInt);
        resData.put("ordinary", userInt);
        return new ReturnInfo(resData);
    }
}
