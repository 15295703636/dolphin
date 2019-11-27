package org.cs.dp.ucenter.service.impl;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.ucenter.domain.CodeBean;
import org.cs.dp.ucenter.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @ClassName ICodeServiceImpl
 * @Description
 * @Author Liujt
 * @Date 2019/11/27 14:38
 **/
@Service
public class ICodeServiceImpl {

    @Autowired
    private ApplicationContext applicationContext;

    public ReturnInfo getCodeInfo(String code) {
        ICodeService iCodeService = getService(code);
        if (null == iCodeService) {
            new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "不存在该代码表!");
        }
        return iCodeService.getCodeInfo();
    }

    public ReturnInfo editCodeInfo(CodeBean param) {
        ICodeService iCodeService = getService(param.getTableName());
        if (null == iCodeService) {
            new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "不存在该代码表!");
        }
        return iCodeService.editCodeInfo(param.getObj());
    }

    public ReturnInfo addCodeInfo(CodeBean param) {
        ICodeService iCodeService = getService(param.getTableName());
        if (null == iCodeService) {
            new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "不存在该代码表!");
        }
        return iCodeService.addCodeInfo(param.getObj());
    }

    public ReturnInfo delCodeInfo(CodeBean param) {
        ICodeService iCodeService = getService(param.getTableName());
        if (null == iCodeService) {
            new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "不存在该代码表!");
        }
        return iCodeService.delCodeInfo((Integer) param.getObj());
    }

    private ICodeService getService(String code) {
        return applicationContext.getBeansOfType(ICodeService.class).get(code);
    }

}
