package org.cs.dp.radar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.Ut12Entity;

public interface IBaioService {
    ReturnInfo sendmsg(String msg,String method);
    ReturnInfo recMsgFromBaio1(String msg);
    ReturnInfo recMsgFromBaio2(String msg);
}
