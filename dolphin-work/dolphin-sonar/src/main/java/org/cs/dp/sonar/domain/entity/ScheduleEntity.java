package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "日程实体")
public class ScheduleEntity {

    @ApiModelProperty(value = "会议id")
    private Integer id;

    @ApiModelProperty(value = "日程名称")
    private String name;

    @ApiModelProperty(value = "日程状态")
    private Integer state;

    @ApiModelProperty(value = "日程日期")
    private String date;

    @ApiModelProperty(value = "日程持续时长")
    private String duration;

    @ApiModelProperty(value = "日程人数")
    private Integer user_number;

    @ApiModelProperty(value = "用户Id")
    private String user_ids;

    @ApiModelProperty(value = "日程主场(端ID)")
    private Integer device_id;

    @ApiModelProperty(value = "参会者端ID")
    private String device_ids;

    @ApiModelProperty(value = "终端数量")
    private Integer device_number;

    @ApiModelProperty(value = "日程类型 1会议 2互动 3录播")
    private Integer type;

    @ApiModelProperty(value = "是否直播")
    private String isLive;

    @ApiModelProperty(value = "是否录制")
    private String isRecord;

    @ApiModelProperty(value = "分辨率")
    private Integer resolving_power;

    @ApiModelProperty(value = "带宽")
    private Integer bandwidth;

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(hidden = true)
    private Date create_time;

    @ApiModelProperty(hidden = true)
    private Date update_time;

    @ApiModelProperty(hidden = true)
    private Integer create_user_id;

    @ApiModelProperty(value = "主讲老师")
    private String teacher_name;

    public ScheduleEntity() {
    }

    public ScheduleEntity(String teacher_name, Integer id, String name, Integer state, String date, String duration, Integer user_number, String user_ids, Integer device_id, String device_ids, Integer device_number, Integer type, String isLive, String isRecord, Integer resolving_power, Integer bandwidth, Integer org_id, Date create_time, Date update_time, Integer create_user_id) {
        this.teacher_name = teacher_name;
        this.id = id;
        this.name = name;
        this.state = state;
        this.date = date;
        this.duration = duration;
        this.user_number = user_number;
        this.user_ids = user_ids;
        this.device_id = device_id;
        this.device_ids = device_ids;
        this.device_number = device_number;
        this.type = type;
        this.isLive = isLive;
        this.isRecord = isRecord;
        this.resolving_power = resolving_power;
        this.bandwidth = bandwidth;
        this.org_id = org_id;
        this.create_time = create_time;
        this.update_time = update_time;
        this.create_user_id = create_user_id;
    }

}