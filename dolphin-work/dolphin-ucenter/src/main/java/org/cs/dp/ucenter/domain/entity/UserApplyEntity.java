package org.cs.dp.ucenter.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "用户申请实体")
public class UserApplyEntity {
    private Integer user_id;

    private String user_name;

    private String user_qname;

    private String user_pwd;

    private String user_email;

    private Integer role_id;

    private Integer org_id;

    private String org_name;

    private Boolean user_statu;

    private String user_number;

    private String user_tel;

    private String user_code;

    @ApiModelProperty(value = "申请结果 0未处理 1通过 2拒绝")
    private Integer apply_result;

    private Date create_time;

    private Date apply_time;

    private String user_desc;

}