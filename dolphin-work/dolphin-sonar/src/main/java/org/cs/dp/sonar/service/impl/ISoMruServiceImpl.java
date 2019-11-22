package org.cs.dp.sonar.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.Ut12Entity;
import org.cs.dp.radar.api.feign.IMruClient;
import org.cs.dp.sonar.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 调用radar服务mru相关接口
 */
@Service
public class ISoMruServiceImpl implements ISoMruService {
    @Autowired
    IMruClient iMruClient;

    @Override
    public ReturnInfo createUt12(Ut12Entity ut12Entity) {
        return iMruClient.createUt12(ut12Entity);
    }
}
