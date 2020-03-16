package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:23
 */
public class RestMyRoomConfig {
    private String password	;//	是	-	会议室的密码，要求是12位以内的数字；空字符串表示没有密码
    private boolean onlyOwnerCanActivate	;//	是	-	是否只允许拥有者激活会议
    private boolean allowAnonymousParty	;//	是	-	是否允许匿名入会
    private boolean toShare	;//	是	-	是否将我的会议室开放给其他用户使用
    private long templateId	;//	是	-	绑定的会议群组的id，此会议群组必须尚未绑定到任何会议室；0表示没有要绑定的会议群组

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
}
