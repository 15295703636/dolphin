package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;

import java.util.Date;

@Data
@ApiModel(value = "点播实体管理")
public class VideoDemandEntity {
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "主讲人")
    private String teacher_name;

    @ApiModelProperty(value = "日程类型")
    private Integer course_type;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "创建人")
    private Integer create_user;

    @ApiModelProperty(value = "时长")
    private String duration;

    @ApiModelProperty(value = "租户ID")
    private Integer customer_id;

    @ApiModelProperty(value = "mp4下载地址")
    private String mp4_url;

    @ApiModelProperty(value = "m3u8播放地址")
    private String m3u8_url;

    public VideoDemandEntity() {
    }

    public VideoDemandEntity(String name, String teacher_name, Integer course_type, String duration, String mp4_url, String m3u8_url) {
        this.name = name;
        this.teacher_name = teacher_name;
        this.course_type = course_type;
        this.create_user = ThreadLocalUserInfoUtil.get().getUser_id();
        this.duration = duration;
        this.customer_id = ThreadLocalUserInfoUtil.get().getCustomer_id();
        this.mp4_url = mp4_url;
        this.m3u8_url = m3u8_url;
    }
}