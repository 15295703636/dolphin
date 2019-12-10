package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "日程历史表")
public class CourseHistoryEntity {
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "主讲老师")
    private String teacher_name;

    @ApiModelProperty(value = "是否录制")
    private String isRecord;

    @ApiModelProperty(value = "是否直播")
    private String isLive;

    @ApiModelProperty(value = "开始时间")
    private String start_time;

    @ApiModelProperty(value = "结束时间")
    private String end_time;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "创建者")
    private Integer createrId;

    @ApiModelProperty(value = "来源")
    private String provenance;

    @ApiModelProperty(value = "主教室id")
    private Integer local_classroomId;

    @ApiModelProperty(value = "远程教室id")
    private String remote_classroomIds;

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(value = "生成的节目id")
    private Integer media_id;

}