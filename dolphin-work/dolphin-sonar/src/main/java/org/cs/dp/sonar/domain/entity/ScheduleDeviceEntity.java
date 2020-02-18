package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ScheduleDeviceEntity {
    private Integer id;

    private Integer device_id = 0;

    private Integer schedule_id = 0;

    @ApiModelProperty(value = "类型 0端，1软终端")
    private Integer type;

    @ApiModelProperty(value = "状态0主讲，1远程")
    private Integer state;

    public ScheduleDeviceEntity() {
    }

    /**
     * 端函数
     *
     * @param device_id
     * @param type
     * @param state
     */
    public ScheduleDeviceEntity(Integer device_id, Integer schedule_id, Integer type, Integer state) {
        this.device_id = device_id;
        this.schedule_id = schedule_id;
        this.type = type;
        this.state = state;
    }

}