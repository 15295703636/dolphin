package org.cs.dp.radar.service;

import org.cs.dolphin.common.base.ReturnInfo;

public interface IBrsService {
    ReturnInfo sendmsg(String msg, String method);
    ReturnInfo sendmsgtopic(String msg, String method,String queue);
    ReturnInfo recMsgFromBrs1(String msg);
    ReturnInfo recMsgFromBrs2(String msg);
}
