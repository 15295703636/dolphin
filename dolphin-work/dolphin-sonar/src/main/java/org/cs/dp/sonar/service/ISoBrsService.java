package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;

public interface ISoBrsService {
    ReturnInfo sendMsgToBrs(String msg, String method);
    ReturnInfo sendMsgToBrsTopic(String msg, String method,String queue);

    ReturnInfo login(BssTaskReq bssTaskReq, String queue);
    ReturnInfo startTask( BssTaskReq bssTaskReq, String queue);
    ReturnInfo stopTask( BssTaskReq bssTaskReq, String queue);
    ReturnInfo queryTask( BssTaskReq bssTaskReq, String queue);
    ReturnInfo deleteTaskTask( BssTaskReq bssTaskReq, String queue);
    ReturnInfo uploadFile( BssTaskReq bssTaskReq, String queue);
    ReturnInfo uploadThumbnail( BssTaskReq bssTaskReq, String queue);
    ReturnInfo catThumbnail( BssTaskReq bssTaskReq, String queue);
}
