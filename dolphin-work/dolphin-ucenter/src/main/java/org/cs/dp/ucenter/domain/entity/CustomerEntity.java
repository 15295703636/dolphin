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

    @ApiModelProperty(value = "租户状态")
    private Integer customer_status;

    @ApiModelProperty(value = "云视讯登录名")
    private String out_name;

    @ApiModelProperty(value = "云视讯密码")
    private String out_pwd;

    @ApiModelProperty(value = "租户地址")
    private String customer_address;

    @ApiModelProperty(value = "有效时间")
    private String effective_time;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;

    public CustomerEntity() {
    }

    public CustomerEntity(Integer id, String customer_id, Integer manage_id, String customer_name, Integer customer_type, Date customer_end_time, Integer customer_status, String out_name, String out_pwd, String customer_address, String effective_time, String remark, Date create_time, Date update_time) {
        this.id = id;
        this.customer_id = customer_id;
        this.manage_id = manage_id;
        this.customer_name = customer_name;
        this.customer_type = customer_type;
        this.customer_end_time = customer_end_time;
        this.customer_status = customer_status;
        this.out_name = out_name;
        this.out_pwd = out_pwd;
        this.customer_address = customer_address;
        this.effective_time = effective_time;
        this.remark = remark;
        this.create_time = create_time;
        this.update_time = update_time;
    }

}