package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.CodeBean;

/**
 * @ClassName ICodeService
 * @Description 代码表控制类
 * @Author Liujt
 * @Date 2019/11/27 14:35
 **/
public interface ICodeService {
    ReturnInfo getCodeInfo();

    ReturnInfo editCodeInfo(Object param);

    ReturnInfo addCodeInfo(Object param);

    ReturnInfo delCodeInfo(Integer param);
}
