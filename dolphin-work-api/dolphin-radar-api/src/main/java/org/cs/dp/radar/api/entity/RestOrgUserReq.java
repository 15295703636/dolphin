package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:19
 */
public class RestOrgUserReq {
    //private long id = 0;//	是	-	用户的id，添加用户时赋值为0，编辑用户时赋值为目标用户的id
    private String name;//	是	-	用户名
    private String displayName;//	是	-	用户昵称
    private String description;//	否	-	用户描述
    private String password;//	是	-	用户密码，须为用SHA-1方式加密的密文
    private int status;//	否	-	取值见常量：用户状态
    private String email;//	是	-	用户的邮箱账号
    private String cellphone;//	是	-	用户的手机号码
    private String role;//	是	-	取值见常量：用户角色
    private Integer deptId;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /*public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
