package org.cs.dp.ucenter.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.springframework.stereotype.Component;

@Component
public class IUserClientFallback implements IUserClient {
    @Override
    public ReturnInfo selectUserByOrg(Integer page, Integer rows,Integer org_id) {
        return ReturnInfo.build(MessageCode.COMMON_FAILURE_FLAG,"获取失败");
    }
}
