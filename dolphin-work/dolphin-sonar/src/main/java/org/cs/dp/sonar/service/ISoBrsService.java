package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;

public interface ISoBrsService {
    String SOBRS_LOGIN = "login";
    String SOBRS_STARTTASK = "startTask";
    String SOBRS_STOPTASK = "stopTask";
    String SOBRS_QUERYTASK = "queryTask";

    ReturnInfo getServer(String method, String uuid, String queue);

    ReturnInfo sendMsgToBrs(String msg, String method);

    ReturnInfo sendMsgToBrsTopic(String msg, String method, String queue);

    ReturnInfo login(BssTaskReq bssTaskReq, String queue);

    ReturnInfo startTask(BssTaskReq bssTaskReq, String queue);

    ReturnInfo stopTask(BssTaskReq bssTaskReq, String queue);

    ReturnInfo queryTask(BssTaskReq bssTaskReq, String queue);

    ReturnInfo deleteTaskTask(BssTaskReq bssTaskReq, String queue);

    ReturnInfo uploadFile(BssTaskReq bssTaskReq, String queue);

    ReturnInfo uploadThumbnail(BssTaskReq bssTaskReq, String queue);

    ReturnInfo catThumbnail(BssTaskReq bssTaskReq, String queue);
}
