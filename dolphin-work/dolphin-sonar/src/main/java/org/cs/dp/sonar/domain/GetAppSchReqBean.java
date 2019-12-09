package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetAppSchBean
 * @Description 查看预约日程入参
 * @Author Liujt
 * @Date 2019/12/9 16:17
 **/
@Data
@ApiModel(value = "查看预约日程入参")
public class GetAppSchReqBean {

    @ApiModelProperty(value = "时间")
    private String date;

    @ApiModelProperty(value = "类型 1会议 2互动 3录播")
    private String type;

}
