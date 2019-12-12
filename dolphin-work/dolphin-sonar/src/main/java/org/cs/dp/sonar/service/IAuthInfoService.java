package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;

/**
 * @ClassName IIAuthInfoService
 * @Description
 * @Author Liujt
 * @Date 2019/12/11 15:03
 **/
public interface IAuthInfoService {

    ReturnInfo getAuthInfo(Integer customerId);

}
