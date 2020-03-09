package org.cs.dp.radar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.Ut12Entity;

public interface IBaioService {
    ReturnInfo sendmsg(String msg, String queue);

    ReturnInfo recMsgFromBaio1(String msg);

    /**
     *
     * @param msg
     * @return
     */
    ReturnInfo keepalive(String msg);
}
