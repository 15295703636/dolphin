package org.cs.dp.radar.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.Ut12Entity;
import org.cs.dp.radar.service.IMruService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class MruServiceImpl implements IMruService {
    @Override
    public ReturnInfo createUt12(Ut12Entity ut12Entity) {
        Object param=null;
        Map resMap = new RestTemplate().postForObject("http://127.0.0.1:8080/mru/api/xxx", param, Map.class);
        return null;
    }
}
