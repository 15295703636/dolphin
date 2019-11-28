package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:32
 */
public class RestEndpoint {
    private long id	;//	ID	-
    private String name	;//	名称	-
    private String ip	;//	IP地址	-
    private boolean available	;//	是否可用，即在指定的时间段内不在任何会议中	-
    private String callProtocol	;//	呼叫协议	取值见常量：呼叫协议
    private String sipUrl	;//	SIP号码	-
    private String deviceStatus	;//	终端状态	取值见常量：终端状态
    private String description	;//	对终端的备注	-
    private long userId	;//	对应用户的id	-
    private String userName	;//	对应用户的用户名	-
    private String deviceSn	;//	终端的序列号	-
    private String endpointType	;//	终端类型	取值见常量：终端类型
    private String e164	;//	E.164号	-
    private long orgId	;//	终端所属公司的id	-
    private String orgName	;//	公司的名称	-
    private long deptId	;//	终端所属部门的id	-
    private String depShortName	;//	部门的简称	-
    private String zoneName	;//	终端所属可用区的名称	-

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCallProtocol() {
        return callProtocol;
    }

    public void setCallProtocol(String callProtocol) {
        this.callProtocol = callProtocol;
    }

    public String getSipUrl() {
        return sipUrl;
    }

    public void setSipUrl(String sipUrl) {
        this.sipUrl = sipUrl;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getEndpointType() {
        return endpointType;
    }

    public void setEndpointType(String endpointType) {
        this.endpointType = endpointType;
    }

    public String getE164() {
        return e164;
    }

    public void setE164(String e164) {
        this.e164 = e164;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getDepShortName() {
        return depShortName;
    }

    public void setDepShortName(String depShortName) {
        this.depShortName = depShortName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
