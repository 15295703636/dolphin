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
    private Integer device_type;

    @ApiModelProperty(value = "端状态")
    private Integer device_state;

    @ApiModelProperty(value = "呼叫协议")
    private Integer call_protocol;

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(value = "租户ID", hidden = true)
    private Integer customer_id;
}
