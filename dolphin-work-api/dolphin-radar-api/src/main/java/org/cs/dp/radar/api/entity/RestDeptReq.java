package org.cs.dp.radar.api.entity;

import lombok.Data;

/**
 * @ClassName RestDeptReq
 * @Description 云视讯组织实体
 * @Author Liujt
 * @Date 2020/3/7 12:30
 **/
@Data
public class RestDeptReq {
    private Long id;//否 部门的id
    private Long parentId;//;//	是	-	上级部门的id，值为0表示没有上级部门
    private Long orgId;//否	-	部门所属公司的id
    private String fullName;//是	-	部门的全称
    private String shortName;//	是	-	部门的简称
    private String description;//	否	-	对部门的描述
}
