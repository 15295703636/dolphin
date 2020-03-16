package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
import org.springframework.stereotype.Component;

@Component
public class IBrsClientFallBack implements IBrsClient {

    @Override
    public ReturnInfo sendmsg(String msg,String method) {
        return null;
    }

    @Override
    public ReturnInfo sendmsgtopic(String msg, String method, String queue) {
        return null;
    }

    @Override
    public ReturnInfo login(BssTaskReq bssTaskReq, String queue) {
        return null;
    }

    @Override
    public ReturnInfo startTask(BssTaskReq bssTaskReq, String queue) {
        return null;
    }

    @Override
    public ReturnInfo stopTask(BssTaskReq bssTaskReq, String queue) {
        return null;
    }

    @Override
    public ReturnInfo queryTask(BssTaskReq bssTaskReq, String queue) {
        return null;
    }

    @Override
    public ReturnInfo deleteTaskTask(BssTaskReq bssTaskReq, String queue) {
        return null;
    }

    @Override
    public ReturnInfo uploadFile(BssTaskReq bssTaskReq, String queue) {
        return null;
    }

    @Override
    public ReturnInfo uploadThumbnail(BssTaskReq bssTaskReq, String queue) {
        return null;
    }

    @Override
    public ReturnInfo catThumbnail(BssTaskReq bssTaskReq, String queue) {
        return null;
    }
}
