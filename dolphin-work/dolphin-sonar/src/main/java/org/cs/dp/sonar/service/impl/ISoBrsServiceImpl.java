package org.cs.dp.sonar.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
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

    @Override
    public ReturnInfo login(BssTaskReq bssTaskReq, String queue) {
        return iBrsClient.login(bssTaskReq, queue);
    }

    @Override
    public ReturnInfo startTask(BssTaskReq bssTaskReq, String queue) {
        return iBrsClient.startTask(bssTaskReq, queue);
    }

    @Override
    public ReturnInfo stopTask(BssTaskReq bssTaskReq, String queue) {
        return iBrsClient.stopTask(bssTaskReq, queue);
    }

    @Override
    public ReturnInfo queryTask(BssTaskReq bssTaskReq, String queue) {
        return iBrsClient.queryTask(bssTaskReq, queue);
    }

    @Override
    public ReturnInfo deleteTaskTask(BssTaskReq bssTaskReq, String queue) {
        return iBrsClient.deleteTaskTask(bssTaskReq, queue);
    }

    @Override
    public ReturnInfo uploadFile(BssTaskReq bssTaskReq, String queue) {
        return iBrsClient.uploadFile(bssTaskReq, queue);
    }

    @Override
    public ReturnInfo uploadThumbnail(BssTaskReq bssTaskReq, String queue) {
        return iBrsClient.uploadThumbnail(bssTaskReq, queue);
    }

    @Override
    public ReturnInfo catThumbnail(BssTaskReq bssTaskReq, String queue) {
        return iBrsClient.catThumbnail(bssTaskReq, queue);
    }
}
