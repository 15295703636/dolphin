package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:20
 */
public class RestRoom {
    private long id	;//	ID	-
    private String name	;//	名称	-
    private int capacity	;//	容量，即允许多少方通话	-
    private String numericId	;//会议室号	-
    private long createTime	;//创建时间	-
    private long expireTime	;//过期时间	-
    private long orgId	;//	所属公司ID	-
    private String orgShortName	;//	所属公司的名称	-
    private RestUser bindedUser	;//	会议室的拥有者	-
    private String templateName	;//	绑定的会议群组的名称	-
    private long templateApplicantId	;//	绑定的会议群组的创建者的ID	-
    private boolean onlyOwnerCanAcitvate	;//	只有拥有者才能激活该会议室	-
    private boolean allowAnonymousParty	;//	允许匿名用户入会	-
    private boolean	toShare	;//其他人也可以在该会议室约会	-
    private long	version	;//版本号	-
    private String	password	;//会议室的密码	-
    private boolean	available	;//该会议室是否未被占用	-
    private List<RestConfSummary> confs	;//	使用该会议室的会议列表

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getNumericId() {
        return numericId;
    }

    public void setNumericId(String numericId) {
        this.numericId = numericId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
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

    public RestUser getBindedUser() {
        return bindedUser;
    }

    public void setBindedUser(RestUser bindedUser) {
        this.bindedUser = bindedUser;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public long getTemplateApplicantId() {
        return templateApplicantId;
    }

    public void setTemplateApplicantId(long templateApplicantId) {
        this.templateApplicantId = templateApplicantId;
    }

    public boolean isOnlyOwnerCanAcitvate() {
        return onlyOwnerCanAcitvate;
    }

    public void setOnlyOwnerCanAcitvate(boolean onlyOwnerCanAcitvate) {
        this.onlyOwnerCanAcitvate = onlyOwnerCanAcitvate;
    }

    public boolean isAllowAnonymousParty() {
        return allowAnonymousParty;
    }

    public void setAllowAnonymousParty(boolean allowAnonymousParty) {
        this.allowAnonymousParty = allowAnonymousParty;
    }

    public boolean isToShare() {
        return toShare;
    }

    public void setToShare(boolean toShare) {
        this.toShare = toShare;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<RestConfSummary> getConfs() {
        return confs;
    }

    public void setConfs(List<RestConfSummary> confs) {
        this.confs = confs;
    }
}
