package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.domain.LogEntity;

public interface ISoUserService {
    ReturnInfo getUserListByOrg();
    ReturnInfo addLog(LogEntity param);
}
