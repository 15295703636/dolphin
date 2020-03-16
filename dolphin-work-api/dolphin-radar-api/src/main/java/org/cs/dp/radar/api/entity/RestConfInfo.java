package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:28
 */
public class RestConfInfo {

    private String confName	;//	会议名称	-
    private String confTime	;//	会议起止时间的文本表示	-
    private String numericId	;//	会议号	-
    private String confPassword	;//	会议密码	-
    private long confId	;//	会议ID	-
    private String remark	;//	会议备注	-
    private String softEndpointJoinUrl	;//	软终端入会链接	-
    private String hardEndpointJoinUrl	;//	会捷通硬终端入会方式	-
    private String h323EndpointJoinUrl	;//	H.323终端入会方式	-
    private String sipEndpointJoinUrl	;//	SIP终端入会方式	-
    private String address	;//	当前访问的可用区的服务地址	-
    private String phoneNumberForMainland	;//	大陆地区的电话接入方式	-
    private String phoneNumberExceptMainland	;//	非大陆地区的电话接入方式
    private String hostZoneAddress	;//	该会议的主可用区地址	-
    private String confStatus	;//	会议状态	取值见常量：会议状态

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getConfTime() {
        return confTime;
    }

    public void setConfTime(String confTime) {
        this.confTime = confTime;
    }

    public String getNumericId() {
        return numericId;
    }

    public void setNumericId(String numericId) {
        this.numericId = numericId;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public long getConfId() {
        return confId;
    }

    public void setConfId(long confId) {
        this.confId = confId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumberForMainland() {
        return phoneNumberForMainland;
    }

    public void setPhoneNumberForMainland(String phoneNumberForMainland) {
        this.phoneNumberForMainland = phoneNumberForMainland;
    }

    public String getPhoneNumberExceptMainland() {
        return phoneNumberExceptMainland;
    }

    public void setPhoneNumberExceptMainland(String phoneNumberExceptMainland) {
        this.phoneNumberExceptMainland = phoneNumberExceptMainland;
    }

    public String getHostZoneAddress() {
        return hostZoneAddress;
    }

    public void setHostZoneAddress(String hostZoneAddress) {
        this.hostZoneAddress = hostZoneAddress;
    }

    public String getConfStatus() {
        return confStatus;
    }

    public void setConfStatus(String confStatus) {
        this.confStatus = confStatus;
    }
}
