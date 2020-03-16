package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
import org.cs.dp.radar.api.feign.IBrsClient;
import org.cs.dp.sonar.service.ISoBrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 调用radar服务brs相关接口
 */
@Slf4j
@Service
public class ISoBrsServiceImpl implements ISoBrsService {
    @Autowired
    IBrsClient iBrsClient;

    @Override
    public ReturnInfo getServer(String method, String uuid, String queue) {
        log.info("流媒体：{}：入参：{}-->{}", method, queue, uuid);
        ReturnInfo returnInfo = null;
        BssTaskReq bssTaskReq = new BssTaskReq();
        bssTaskReq.setUsername("admin");
        bssTaskReq.setPasswd("123456");
        bssTaskReq.setTask_id(uuid);
        queue = "rpc_queue_".concat(queue);
        switch (method) {
            case SOBRS_LOGIN:
                returnInfo = login(bssTaskReq, queue);
                break;
            case SOBRS_STARTTASK:
                returnInfo = startTask(bssTaskReq, queue);
                break;
            case SOBRS_STOPTASK:
                returnInfo = stopTask(bssTaskReq, queue);
                break;
            case SOBRS_QUERYTASK:
                returnInfo = queryTask(bssTaskReq, queue);
                break;
        }
        log.info("流媒体：{}：出参：{}-->{}", method, queue, JSON.toJSONString(returnInfo));
        return returnInfo;
    }

    @Override
    public ReturnInfo sendMsgToBrs(String msg, String method) {
        return iBrsClient.sendmsg(msg, method);
    }

    @Override
    public ReturnInfo sendMsgToBrsTopic(String msg, String method, String queue) {
        return iBrsClient.sendmsgtopic(msg, method, queue);
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
