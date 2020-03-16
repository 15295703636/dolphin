package org.cs.dp.sonar.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Service;

@Service
//value:业务标志
@JobHandler(value = "testJobHandler")
public class TestJobHandler extends IJobHandler {
    /**
     * 执行参数
     * @param param
     * @return
     */
    @Override
    public ReturnT<String> execute(String param) {
        XxlJobLogger.log("定时任务执行参数：{}", param);
        //TODO 执行业务处理
        return SUCCESS;
    }
}