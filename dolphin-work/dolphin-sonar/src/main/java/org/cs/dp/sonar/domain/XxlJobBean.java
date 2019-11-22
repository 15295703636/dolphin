package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName XxlJobBean
 * @Description 添加定时任务job
 * @Author Liujt
 * @Date 2019/11/21 17:38
 **/
@Data
public class XxlJobBean {

    @ApiModelProperty(value = "任务执行CRON表达式", required = true)
    private String jobCron;

    @ApiModelProperty(value = "任务描述:开课/结束；必填", hidden= true)
    private String jobDesc;

    @ApiModelProperty(value = "任务执行器的ID,server端执行器页面配置好后会自动生成", hidden = true)
    private int jobGroup;

    @ApiModelProperty(value = "负责人==创建人", hidden = true)
    private String author;

    @ApiModelProperty(value = "任务Handler名称 ：调用哪一个执行器", hidden = true)
    private String executorHandler;

    @ApiModelProperty(value = "执行器，任务参数 ：任务执行的参数，一个执行器的数据结构必须相同", hidden = true)
    private String executorParam="";

    @ApiModelProperty(value = "任务执行超时时间，单位秒", hidden = true)
    private int executorTimeout = 60;

    @ApiModelProperty(value = "失败重试次数", hidden = true)
    private int executorFailRetryCount = 3;     // 失败重试次数

    private String glueType = "BEAN";

    private String executorRouteStrategy = "FIRST";

    private String executorBlockStrategy = "SERIAL_EXECUTION";
}
