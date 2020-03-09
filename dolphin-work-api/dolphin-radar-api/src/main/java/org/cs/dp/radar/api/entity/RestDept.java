package org.cs.dp.radar.api.entity;

import lombok.Data;

/**
 * @ClassName RestDept
 * @Description 添加组织返回实体
 * @Author Liujt
 * @Date 2020/3/7 14:12
 **/
@Data
public class RestDept {
    private Long id	;//long	部门的id	-
    private Long parentId;//	long	上级部门的id，值为0表示没有上级部门	-
    private Long orgId;//	long	部门所属公司的id	-
    private String fullName;//	String	部门的全称	-
    private String shortName;//	String	部门的简称	-
    private String description;//	String	对部门的描述	-
    private String parentFullName;//	String	上级部门的全称	-
    private String parentShortName;//	String	上级部门的简称
}
