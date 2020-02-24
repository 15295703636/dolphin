package org.cs.dp.ucenter.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;
import org.cs.dp.ucenter.domain.entity.UserEntity;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AddUserBean
 * @Description 添加用户入参实体
 * @Author Liujt
 * @Date 2019/12/6 10:23
 **/
@Data
@ApiModel(value = "添加用户入参实体")
public class AddUserBean extends UserEntity {

    @ExcelProperty(index = 3)
    @NotNull(message = Constant.ORG_ID_ISEMPTY_MSG)

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(value = "租户ID")
    private Integer customer_id;
}
