package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.entity.CodeEntity;

/**
* @ClassName ICodeService
* @Description 代码信息维护接口
* @Author Liujt
* @Date 2019-12-09 09:37:29
**/
public interface ICodeService {

    ReturnInfo addCode(CodeEntity param);

    ReturnInfo delCode(Integer param);

    ReturnInfo editCode(CodeEntity param);

    ReturnInfo getCode(String param);

}
