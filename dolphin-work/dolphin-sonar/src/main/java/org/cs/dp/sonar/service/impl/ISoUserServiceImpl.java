package org.cs.dp.sonar.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dp.sonar.service.ISoUserService;
import org.cs.dp.ucenter.api.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 调用ucenter服务用户相关接口
 */
@Service
public class ISoUserServiceImpl implements ISoUserService {
    @Autowired
    IUserClient iUserClient;

    @Override
    public ReturnInfo getUserListByOrg() {
        return iUserClient.selectUserByOrg(0, 0, 0);
    }

    @Async
    @Override
    public ReturnInfo addLog(LogEntity param) {
        return iUserClient.allLog(param);
    }
}
