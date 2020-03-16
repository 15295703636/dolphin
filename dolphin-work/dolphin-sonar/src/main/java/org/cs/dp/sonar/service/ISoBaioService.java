package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;

public interface ISoBaioService {
    ReturnInfo sendMsgToBaio(String msg, String queue);

    ReturnInfo recordedBroadcastCourse(String message);

    ReturnInfo keepalive(String message,Integer type);
}
