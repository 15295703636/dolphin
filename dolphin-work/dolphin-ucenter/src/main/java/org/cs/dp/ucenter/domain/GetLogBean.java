package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName GetLogBean
 * @Description 查询日志条件
 * @Author Liujt
 * @Date 2019/11/27 17:10
 **/
@Data
@ApiModel(value = "查询日志条件")
public class GetLogBean {
    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
