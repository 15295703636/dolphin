package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "组织实体")
public class OrganizationEntity  {
    @ApiModelProperty(value = "id")
    private Integer org_id;

    @ApiModelProperty(value = "名称")
    private String org_name;

    @ApiModelProperty(value = "类型;枚举:中心、区域、学校、部门，1、2、3、4")
    private Integer org_type;

    @ApiModelProperty(value = "父节点;0为根节点")
    private Integer org_preid;

    @ApiModelProperty(value = "租户id")
    private Integer customer_id;

    private List<OrganizationEntity> node;

    public OrganizationEntity(Integer org_id, String org_name, Integer org_type, Integer org_preid, Integer customer_id) {
        this.org_id = org_id;
        this.org_name = org_name;
        this.org_type = org_type;
        this.org_preid = org_preid;
        this.customer_id = customer_id;
    }


}