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
@ApiModel(value ="")
public class ScheduleArrayBean extends ScheduleEntity {
    @ApiModelProperty(value = "端ID")
    private List<Integer> deviceIds;
}
