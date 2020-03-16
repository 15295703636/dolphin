package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName CustomerAuthorBean
 * @Description TODO
 * @Author Liujt
 * @Date 2020/1/19 15:03
 **/
@Data
@ApiModel(value = "租户授权入参")
public class CustomerAuthorBean {

    @NotBlank(message = "租户ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

    @NotBlank(message = "租户开始时间不能为空")
    @ApiModelProperty(value = "租户开始时间", required = true, example = "yyyy-MM-dd HH:mm:ss")
    private String customer_start_time;

    @NotBlank(message = "租户结束时间不能为空")
    @ApiModelProperty(value = "租户结束时间", required = true, example = "yyyy-MM-dd HH:mm:ss")
    private String customer_end_time;
}
