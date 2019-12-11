package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetCourseBean
 * @Description 查询正在进行的日程
 * @Author Liujt
 * @Date 2019/12/10 20:28
 **/
@Data
public class GetCourseBean {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "查询内容")
    private String content;

}
