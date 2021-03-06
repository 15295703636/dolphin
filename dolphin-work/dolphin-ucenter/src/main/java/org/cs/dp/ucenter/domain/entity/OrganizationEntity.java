package org.cs.dp.ucenter.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel(value = "组织实体")
public class OrganizationEntity {
    @ApiModelProperty(value = "id")
    private Integer org_id;

    @ApiModelProperty(value = "云视讯Id")
    private Long ysx_id;

    @ApiModelProperty(value = "名称")
    private String org_name;

    @ApiModelProperty(value = "类型;枚举:中心、区域、学校、部门，1、2、3、4")
    private Integer org_type;

    //@NotBlank(message = Constant.ORG_PREID_ID_MSG)
    @ApiModelProperty(value = "父节点;0为根节点")
    private Integer org_preid;

    @ApiModelProperty(value = "租户id")
    private Integer customer_id;

    private List<OrganizationEntity> node;

    public OrganizationEntity() {
    }

    public OrganizationEntity(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public OrganizationEntity(Integer org_id, String org_name, Integer org_type, Integer org_preid, Integer customer_id) {
        this.org_id = org_id;
        this.org_name = org_name;
        this.org_type = org_type;
        this.org_preid = org_preid;
        this.customer_id = customer_id;
    }


}