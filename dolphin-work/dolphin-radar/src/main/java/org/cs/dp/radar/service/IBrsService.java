package org.cs.dp.radar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IBrsService {
    ReturnInfo sendmsg(String msg, String method);
    ReturnInfo sendmsgtopic(String msg, String method,String queue);
    ReturnInfo keepalive(String msg);
    ReturnInfo recMsgFromBrs2(String msg);
    ReturnInfo login( BssTaskReq bssTaskReq,  String queue);

    ReturnInfo startTask( BssTaskReq bssTaskReq, String queue);
    ReturnInfo stopTask( BssTaskReq bssTaskReq, String queue);
    ReturnInfo queryTask( BssTaskReq bssTaskReq, String queue);
    ReturnInfo deleteTaskTask( BssTaskReq bssTaskReq, String queue);
    ReturnInfo uploadFile( BssTaskReq bssTaskReq, String queue);
    ReturnInfo uploadThumbnail( BssTaskReq bssTaskReq, String queue);
    ReturnInfo catThumbnail( BssTaskReq bssTaskReq, String queue);

}
