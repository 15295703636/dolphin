package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.CodeTableEntity;

/**
* @ClassName ICodeTableService
* @Description 代码表接口
* @Author Liujt
* @Date 2019-12-09 09:36:10
**/
public interface ICodeTableService {

    ReturnInfo addCodeTable(CodeTableEntity param);

    ReturnInfo delCodeTable(String param);

    ReturnInfo editCodeTable(CodeTableEntity param);

    ReturnInfo getCodeTable(String explain);

}
