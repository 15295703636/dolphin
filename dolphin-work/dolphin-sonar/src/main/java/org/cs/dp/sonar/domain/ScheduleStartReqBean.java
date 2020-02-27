package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ScheduleStartReqBean
 * @Description 日程开会
 * @Author Liujt
 * @Date 2020/2/27 16:08
 **/
@Data
@ApiModel(value = "日程开会")
public class ScheduleStartReqBean {
    @ApiModelProperty(value = "日程配置Id/历史信息Id")
    private Integer id;
    @ApiModelProperty(value = "历史开会标志 1历史 其他值日程配置开会")
    private int historySign;

    public ScheduleStartReqBean() {
    }

    public ScheduleStartReqBean(Integer id, int historySign) {
        this.id = id;
        this.historySign = historySign;
    }
}
