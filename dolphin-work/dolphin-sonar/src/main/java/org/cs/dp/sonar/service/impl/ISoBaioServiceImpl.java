package org.cs.dp.sonar.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.feign.IBaioClient;
import org.cs.dp.sonar.api.feign.ISomqClient;
import org.cs.dp.sonar.service.ISoBaioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 调用radar服务baio相关接口
 */
@Service
public class ISoBaioServiceImpl implements ISoBaioService {
    @Autowired
    IBaioClient iBaioClient;
    @Override
    public ReturnInfo sendMsgToBaio(String msg,String method) {
        return iBaioClient.sendmsg(msg,method);
    }
}
