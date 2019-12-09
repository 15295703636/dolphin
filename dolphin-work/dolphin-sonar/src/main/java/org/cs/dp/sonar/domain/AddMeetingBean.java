package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.sonar.domain.entity.MeetingEntity;

import java.util.List;

/**
 * @ClassName AddMeetingBean
 * @Description 添加会议入参
 * @Author Liujt
 * @Date 2019/12/9 13:37
 **/
@Data
@ApiModel(value = "添加会议入参")
public class AddMeetingBean {
    @ApiModelProperty(value = "会议主信息")
    private MeetingEntity meetingEntity;

    @ApiModelProperty(value = "设备ID")
    private List<Integer> device_ids;

    @ApiModelProperty(value = "用户ID")
    private List<Integer> user_ids;

}
