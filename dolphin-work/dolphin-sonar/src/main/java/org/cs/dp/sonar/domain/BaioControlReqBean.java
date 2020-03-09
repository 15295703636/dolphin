package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BaioControlReqBean
 * @Description
 * @Author Liujt
 * @Date 2020/3/4 13:17
 **/
@Data
@ApiModel(value = "录播暂停继续入参")
public class BaioControlReqBean {
    @ApiModelProperty(value = "录制状态 pause暂停 start继续")
    private String record_state;

    @ApiModelProperty(value = "日程id")
    private Integer course_id;
}
