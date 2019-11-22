package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.radar.api.entity.Ut12Entity;
import org.springframework.stereotype.Component;

@Component
public class IMruClientFallBack implements IMruClient {

    @Override
    public ReturnInfo createUt12(Ut12Entity ut12Entity) {
        return ReturnInfo.build(MessageCode.COMMON_FAILURE_FLAG,"获取失败");
    }
}
