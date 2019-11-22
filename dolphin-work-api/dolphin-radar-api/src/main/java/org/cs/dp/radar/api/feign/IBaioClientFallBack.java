package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.radar.api.entity.Ut12Entity;
import org.springframework.stereotype.Component;

@Component
public class IBaioClientFallBack implements IBaioClient {

    @Override
    public ReturnInfo sendmsg(String msg) {
        return null;
    }
}
