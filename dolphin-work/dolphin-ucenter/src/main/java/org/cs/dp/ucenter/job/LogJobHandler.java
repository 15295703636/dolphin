package org.cs.dp.ucenter.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.cs.dolphin.common.constant.ModuleConstant;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dolphin.common.utils.ExceptionUtil;
import org.cs.dp.ucenter.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@JobHandler(value = "logJobHandler")
public class LogJobHandler extends IJobHandler {

    @Autowired
    private ILogService iLogService;

    /**
     * 执行参数
     *
     * @param param
     * @return
     */
    @Override
    public ReturnT<String> execute(String param) {
        XxlJobLogger.log("日志删除定时任务执行参数：{}", param);
        try {
            int res = iLogService.delLogByDay(param);
            XxlJobLogger.log("日志删除定时任务返回：{}", res);
        } catch (Exception e) {
            XxlJobLogger.log("日志删除定时任务异常：{}", e);
            iLogService.addLog(
                    new LogEntity(ModuleConstant.MODULE_UCENTER,
                            "logJobHandler",
                            ExceptionUtil.getStackTrace(e)
                    ));
            return FAIL;
        }
        return SUCCESS;
    }
}