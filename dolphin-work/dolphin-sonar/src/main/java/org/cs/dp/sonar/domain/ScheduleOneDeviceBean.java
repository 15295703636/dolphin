package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.sonar.domain.entity.DeviceEntity;
import org.cs.dp.sonar.domain.entity.UserEntity;

import java.util.List;

/**
 * @ClassName ScheduleOneDeviceBean
 * @Description 日程单条返回实体
 * @Author Liujt
 * @Date 2020/2/19 14:16
 **/
@Data
@ApiModel(value = "日程单条返回实体")
public class ScheduleOneDeviceBean extends ScheduleArrayBean{
    @ApiModelProperty(value = "设备信息")
    private List<DeviceEntity> devices;

    @ApiModelProperty(value = "用户信息")
    private List<UserEntity> users;
}
