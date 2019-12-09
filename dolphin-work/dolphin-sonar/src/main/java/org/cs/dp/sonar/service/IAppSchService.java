package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.GetAppSchReqBean;

/**
 * @ClassName IAppSchService
 * @Description 预约课程主页
 * @Author Liujt
 * @Date 2019/12/9 16:20
 **/
public interface IAppSchService {

    ReturnInfo getAppSch(GetAppSchReqBean param);

}
