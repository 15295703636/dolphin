package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dp.ucenter.domain.GetLogBean;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ILogService
 * @Description 日志接口
 * @Author Liujt
 * @Date 2019/11/27 17:07
 **/
public interface ILogService {

    ReturnInfo getLog(RequestPage<SplitPageInfo, GetLogBean> param);

    ReturnInfo addLog(LogEntity param);

    void exportLog(String moduleName,String startTime,String endTime, HttpServletResponse response) throws Exception;

    int delLogByDay(String day);
}
