package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "日程历史表")
public class CourseHistoryEntity {
    private Integer course_id;

    @ApiModelProperty(value = "日程名称")
    private String course_name;

    @ApiModelProperty(value = "日程类型 1会议 2互动 3录播")
    private Integer course_type;

    @ApiModelProperty(value = "状态")
    private Integer course_state;

    @ApiModelProperty(value = "主讲老师")
    private String teacher_name;

    @ApiModelProperty(value = "是否录制")
    private String isRecord;

    @ApiModelProperty(value = "是否直播")
    private String isLive;

    @ApiModelProperty(value = "录制状态")
    private String record_state;

    @ApiModelProperty(value = "直播状态")
    private String live_state;

    @ApiModelProperty(value = "是否片头片尾")
    private String isSubtitle;

    @ApiModelProperty(value = "是否自动提交审核")
    private String isAutoSubmit;

    @ApiModelProperty(value = "是否发布")
    private String isPublish;

    @ApiModelProperty(value = "是否评论")
    private String isComment;

    @ApiModelProperty(value = "是否评分")
    private String isRate;

    @ApiModelProperty(value = "开始时间")
    private String start_time;

    @ApiModelProperty(value = "结束时间")
    private String end_time;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "日程字体颜色")
    private String course_fontColor;

    @ApiModelProperty(value = "日程字体大小")
    private String course_wordSize;

    @ApiModelProperty(value = "主讲老师字体颜色")
    private String teacher_fontColor;

    @ApiModelProperty(value = "主讲老师字体大小")
    private String teacher_wordSize;

    @ApiModelProperty(value = "分辨率")
    private Integer resolving_power;

    @ApiModelProperty(value = "编码参数")
    private String encode_quality;

    @ApiModelProperty(value = "扩展字段")
    private String otherParamStr;

    @ApiModelProperty(value = "")
    private String epilogue_img;

    @ApiModelProperty(value = "")
    private String prologue_img;

    @ApiModelProperty(value = "创建者")
    private Integer createrId;

    @ApiModelProperty(value = "来源")
    private String provenance;

    @ApiModelProperty(value = "主教室id")
    private Integer local_classroomId;

    @ApiModelProperty(value = "主教室名称")
    private String local_name;

    @ApiModelProperty(value = "远程教室id")
    private String remote_classroomIds;

    @ApiModelProperty(value = "")
    private String isDual;

    @ApiModelProperty(value = "课程名字体")
    private String course_fontType;

    @ApiModelProperty(value = "老师名字体")
    private String teacher_fontType;

    @ApiModelProperty(value = "教室名字体")
    private String label_fontType;

    @ApiModelProperty(value = "教室名字颜色")
    private String label_fontColor;

    @ApiModelProperty(value = "教室名字体大小")
    private String label_wordSize;

    @ApiModelProperty(value = "是否正在进行直播转发")
    private String forward_state;

    @ApiModelProperty(value = "直播观看密码")
    private String live_password;

    @ApiModelProperty(value = "带宽")
    private Integer bandwidth;

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(value = "会议开启云视讯返回的ID")
    private Long ysx_id;

}