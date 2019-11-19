package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PostTestBean
 * @Description 测试post方法实体
 * @Author Liujt
 * @Date 2019/11/19 19:10
 **/
@Data
@ApiModel(description = "测试post方法实体")
public class PostTestBean {

    /**
     * value–字段说明
     * name–重写属性名字（一般不用）
     * dataType–重写属性类型（一般不用）
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     */
    @ApiModelProperty(value = "用户ID", hidden = true)
    private String id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "身份证", required = true)
    private String idCard;

    @ApiModelProperty(value = "生日", example = "yyyy-mm-dd")
    private String birthday;

}
