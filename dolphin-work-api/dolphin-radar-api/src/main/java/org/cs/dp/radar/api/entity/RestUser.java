package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:23
 */
public class RestUser {
    private long id;//	ID	-
    private String name;//	用户名	-
    private boolean passwordModifiedByUser;//	用户是否已修改密码	-
    private String displayName;//	用户昵称	-
    private String email;//	邮箱账号	-
    private String telephone;//	固定电话号码	-
    private String cellphone;//	手机号码	-
    private String description;//	备注	-
    private int status;//	用户状态	取值见页面：用户状态
    private long createTime;//	该用户的创建时刻	以毫秒为单位的Unix时间戳
    private long lastModifiedTime;//	该用户的最近修改时刻	以毫秒为单位的Unix时间戳
    private long version;//	该条用户数据的版本号	-
    private String role;//	角色	取值见页面：用户角色
    private long roomId;//	该用户的个人会议室的id	-
    private long orgId;//	该用户所属公司的id	-
    private String orgShortName;//	公司简称	-
    private long orgAdminUserId;//	公司管理员的id	-
    private String orgPortAllocMode;//	公司的启会模式	-
    private long orgPortCount;//	公司的会议端口数	-
    private long agentId;//	公司所属的客服代表的id	-
    private String agentShortName;//		客服代表的简称	-
    private long deptId;//	用户所属部门的id	-
    private String deptShortName;//	部门的简称	-
    private String domainName;//		客服代表所属域的名称	-
    private List<RestOrgLicenseSum> orgLicenseSumList;//		客服代表分配给下属公司的许可的汇总信息	-
    private String type;//		用户的类型	"normal"表示正常用户，"anonymous"表示匿名用户
    private RestAgentUserLicense agentUserLicense;//	授予客服代表的许可

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPasswordModifiedByUser() {
        return passwordModifiedByUser;
    }

    public void setPasswordModifiedByUser(boolean passwordModifiedByUser) {
        this.passwordModifiedByUser = passwordModifiedByUser;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public long getOrgAdminUserId() {
        return orgAdminUserId;
    }

    public void setOrgAdminUserId(long orgAdminUserId) {
        this.orgAdminUserId = orgAdminUserId;
    }

    public String getOrgPortAllocMode() {
        return orgPortAllocMode;
    }

    public void setOrgPortAllocMode(String orgPortAllocMode) {
        this.orgPortAllocMode = orgPortAllocMode;
    }

    public long getOrgPortCount() {
        return orgPortCount;
    }

    public void setOrgPortCount(long orgPortCount) {
        this.orgPortCount = orgPortCount;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getAgentShortName() {
        return agentShortName;
    }

    public void setAgentShortName(String agentShortName) {
        this.agentShortName = agentShortName;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getDeptShortName() {
        return deptShortName;
    }

    public void setDeptShortName(String deptShortName) {
        this.deptShortName = deptShortName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public List<RestOrgLicenseSum> getOrgLicenseSumList() {
        return orgLicenseSumList;
    }

    public void setOrgLicenseSumList(List<RestOrgLicenseSum> orgLicenseSumList) {
        this.orgLicenseSumList = orgLicenseSumList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RestAgentUserLicense getAgentUserLicense() {
        return agentUserLicense;
    }

    public void setAgentUserLicense(RestAgentUserLicense agentUserLicense) {
        this.agentUserLicense = agentUserLicense;
    }
}
