package org.cs.dp.sonar.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.service.IAuthInfoService;
import org.springframework.stereotype.Service;

/**
 * @ClassName IAuthInfoServiceImpl
 * @Description 租户授权信息
 * @Author Liujt
 * @Date 2019/12/11 15:07
 **/
@Service
public class IAuthInfoServiceImpl implements IAuthInfoService {
    @Override
    public ReturnInfo getAuthInfo(Integer customerId) {
        return new ReturnInfo();
    }
}
