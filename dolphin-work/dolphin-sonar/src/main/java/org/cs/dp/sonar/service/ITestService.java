package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.XxlJobBean;

public interface ITestService {
    ReturnInfo addJob(XxlJobBean param);
}
