package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.sonar.domain.entity.ScheduleEntity;

import java.util.List;

/**
 * @ClassName ScheduleArrayBean
 * @Description 端数组存储
 * @Author Liujt
 * @Date 2020/2/12 17:57
 **/
@Data
@ApiModel(value ="端数组存储")
public class ScheduleArrayBean extends ScheduleEntity {
    @ApiModelProperty(value = "端ID")
    private List<Integer> deviceIds;

    @ApiModelProperty(value = "用户ID")
    private List<Integer> userIds;

    @ApiModelProperty(value = "现在开会标志 true开会")
    private boolean new_start;

    @ApiModelProperty(value = "持续时长—小时")
    private Integer duration_hour;

    @ApiModelProperty(value = "持续时长—分钟")
    private Integer duration_minute;
}
