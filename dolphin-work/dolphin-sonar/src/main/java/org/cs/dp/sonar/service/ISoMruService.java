package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.Ut12Entity;

public interface ISoMruService {
    ReturnInfo createUt12(Ut12Entity ut12Entity);
}
