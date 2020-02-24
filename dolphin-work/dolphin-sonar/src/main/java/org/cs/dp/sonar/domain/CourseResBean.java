package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CourseResBean
 * @Description 进行中的端查询返回
 * @Author Liujt
 * @Date 2020/2/19 10:38
 **/
@Data
@ApiModel(value = "进行中的端查询返回")
public class CourseResBean {
    private Integer device_id;

    @ApiModelProperty(value = "在线状态1在线 2离线(具体值可能会调整)")
    private Integer status;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "设备类型")
    private Integer type;

    @ApiModelProperty(value = "端标志0端，1软终端")
    private Integer isDevice;

    private Integer user_id;

    private Integer role_id;

    private String user_qname;

    private String user_name;

    @ApiModelProperty(value = "设备静音")
    private Integer isMute;//静音

    @ApiModelProperty(value = "设备旁观")
    private Integer isSideLines;//旁观

    private Integer isMain;
}
