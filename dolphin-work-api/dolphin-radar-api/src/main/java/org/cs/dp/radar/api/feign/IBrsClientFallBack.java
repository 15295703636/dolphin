package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
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
}
