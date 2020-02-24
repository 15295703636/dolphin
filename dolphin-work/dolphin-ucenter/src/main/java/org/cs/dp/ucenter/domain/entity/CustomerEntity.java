package org.cs.dp.ucenter.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@ApiModel(value = "租户管理")
public class CustomerEntity {
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "客户代表")
    private String user_qname;

    @ApiModelProperty(value = "客户手机")
    private String user_tel;

    @ApiModelProperty(value = "租户ID")
    private String customer_id;

    @ApiModelProperty(value = "客户代表ID")
    private Integer manage_id;

    @NotBlank(message = Constant.CUSTOMER_NAME_ISEMPTY_MSG)
    @ApiModelProperty(value = "租户名称")
    private String customer_name;

    @ApiModelProperty(value = "租户类型")
    private Integer customer_type;

    @ApiModelProperty(value = "租户开始时间")
    private Date customer_start_time;

    @ApiModelProperty(value = "租户结束时间")
    private Date customer_end_time;

    @ApiModelProperty(value = "租户开始时间String")
    private String customer_start_time_str;

    @ApiModelProperty(value = "租户结束时间String")
    private String customer_end_time_str;

    @ApiModelProperty(value = "租户状态")
    private Integer customer_status;

    @ApiModelProperty(value = "云视讯登录名")
    private String out_name;

    @ApiModelProperty(value = "云视讯密码")
    private String out_pwd;

    @ApiModelProperty(value = "租户地址")
    private String customer_address;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;

}