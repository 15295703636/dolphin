package org.cs.dp.sonar.domain.index;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName IndexCountResBean
 * @Description
 * @Author Liujt
 * @Date 2020/3/6 13:32
 **/
@Data
public class IndexCountDateResBean {
    @ApiModelProperty(value = "时间")
    private String date;

    @ApiModelProperty(value = "类型")
    private Integer course_type;

    @ApiModelProperty(value = "数量")
    private Integer sum;

    @ApiModelProperty(value = "时长")
    private Long duration;
}
