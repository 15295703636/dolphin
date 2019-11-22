package org.cs.dp.radar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.Ut12Entity;

public interface IMruService {
    ReturnInfo createUt12(Ut12Entity ut12Entity);
}
