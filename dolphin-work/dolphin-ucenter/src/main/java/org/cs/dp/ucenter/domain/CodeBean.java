package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CodeBean
 * @Description 代码数据控制统一参数
 * @Author Liujt
 * @Date 2019/11/27 14:03
 **/
@Data
@ApiModel(value = "表")
public class CodeBean<T> {

    @ApiModelProperty(value = "代码表表名")
    private String tableName;

    @ApiModelProperty(value = "操作具体数据")
    private T obj;
}
