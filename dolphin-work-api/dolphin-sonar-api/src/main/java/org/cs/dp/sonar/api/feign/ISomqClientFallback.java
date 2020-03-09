package org.cs.dp.sonar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.springframework.stereotype.Component;

@Component
public class ISomqClientFallback implements ISomqClient {

    @Override
    public ReturnInfo receiveMsg(String msg) {
        return null;
    }

    @Override
    public ReturnInfo keepalive(String msg, Integer type) {
        return null;
    }
}
