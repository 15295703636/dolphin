package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetDeviceByNameStateReqBean
 * @Description
 * @Author Liujt
 * @Date 2020/3/7 11:25
 **/
@Data
@ApiModel(value = "根据名称，状态筛选端入参")
public class GetDeviceByNameStateReqBean {

    @ApiModelProperty(value = "会议id")
    private Integer course_id;

    @ApiModelProperty(value = "端名称")
    private String name;

    @ApiModelProperty(value = "在线状态 0全部 1在线 2离线 ")
    private Integer status = 0;

}
