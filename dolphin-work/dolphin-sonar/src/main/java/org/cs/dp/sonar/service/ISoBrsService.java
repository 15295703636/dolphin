package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;

public interface ISoBrsService {
    ReturnInfo sendMsgToBrs(String msg, String method);
    ReturnInfo sendMsgToBrsTopic(String msg, String method,String queue);
}
