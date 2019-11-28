package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:27
 */
public class RestRoomInfo {
    private String name	;//	会议室名称	-
    private String numericId	;//	会议室号码	-
    private String password	;//	会议室密码	-
    private String softEndpointJoinUrl	;//	软终端入会链接	-
    private String hardEndpointJoinUrl	;//	会捷通硬终端入会方式	-
    private String h323EndpointJoinUrl	;//	H.323终端入会方式	-
    private String sipEndpointJoinUrl	;//	SIP终端入会方式

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumericId() {
        return numericId;
    }

    public void setNumericId(String numericId) {
        this.numericId = numericId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSoftEndpointJoinUrl() {
        return softEndpointJoinUrl;
    }

    public void setSoftEndpointJoinUrl(String softEndpointJoinUrl) {
        this.softEndpointJoinUrl = softEndpointJoinUrl;
    }

    public String getHardEndpointJoinUrl() {
        return hardEndpointJoinUrl;
    }

    public void setHardEndpointJoinUrl(String hardEndpointJoinUrl) {
        this.hardEndpointJoinUrl = hardEndpointJoinUrl;
    }

    public String getH323EndpointJoinUrl() {
        return h323EndpointJoinUrl;
    }

    public void setH323EndpointJoinUrl(String h323EndpointJoinUrl) {
        this.h323EndpointJoinUrl = h323EndpointJoinUrl;
    }

    public String getSipEndpointJoinUrl() {
        return sipEndpointJoinUrl;
    }

    public void setSipEndpointJoinUrl(String sipEndpointJoinUrl) {
        this.sipEndpointJoinUrl = sipEndpointJoinUrl;
    }
}
