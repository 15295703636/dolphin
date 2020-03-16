package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:24
 */
public class RestMyRoomConfigDisplay {
    private String password	;//	会议室的密码	空字符串表示没有密码
    private boolean onlyOwnerCanActivate	;//	是否只允许拥有者激活会议	-
    private boolean allowAnonymousParty	;//	是否允许匿名入会	-
    private boolean toShare	;//	是否将我的会议室开放给其他用户使用	-
    private long templateId	;//	绑定的会议群组的id	0表示该会议室没有被绑定到会议群组
    private String templateName	;//	绑定的会议群组的名称	-

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnlyOwnerCanActivate() {
        return onlyOwnerCanActivate;
    }

    public void setOnlyOwnerCanActivate(boolean onlyOwnerCanActivate) {
        this.onlyOwnerCanActivate = onlyOwnerCanActivate;
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

    public long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
