package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetDeviceBean
 * @Description 查询端信息入参
 * @Author Liujt
 * @Date 2019/12/6 17:16
 **/
@Data
@ApiModel(value = "查询端信息入参")
public class GetDeviceBean {

    @ApiModelProperty(value = "端ID")
    private Integer id;

    @ApiModelProperty(value = "端名称")
    private String name;

    @ApiModelProperty(value = "端型号")
    private String device_type;

    @ApiModelProperty(value = "端状态")
    private String device_state;
}
