package org.cs.dp.sonar.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.feign.IBrsClient;
import org.cs.dp.sonar.service.ISoBrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 调用radar服务brs相关接口
 */
@Service
public class ISoBrsServiceImpl implements ISoBrsService {
    @Autowired
    IBrsClient iBrsClient;

    @Override
    public ReturnInfo sendMsgToBrs(String msg, String method) {
        return iBrsClient.sendmsg(msg,method);
    }

    @Override
    public ReturnInfo sendMsgToBrsTopic(String msg, String method, String queue) {
        return iBrsClient.sendmsgtopic(msg,method,queue);
    }
}
