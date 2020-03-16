package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName EditStatusBean
 * @Description 编辑状态入参
 * @Author Liujt
 * @Date 2019/12/5 20:00
 **/
@Data
@ApiModel(value = "编辑状态入参")
public class EditStatusBean {

    @NotEmpty(message = Constant.CUSTOMER_STATUS_ISEMPTY_MSG)
    @ApiModelProperty(value = "租户状态：1启用 2停用")
    private Integer customer_status;

    @NotEmpty(message = Constant.CUSTOMER_ID_ISEMPTY_MSG)
    @ApiModelProperty(value = "租户ID")
    private List<Integer> ids;

}
