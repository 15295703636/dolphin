package org.cs.dp.radar.api.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10=32
 */
public class RestEndpoint {
    @ApiModelProperty(required = true)
    private Long id;//= new Long(23272);
    @ApiModelProperty(required = true)
    private String name;//= "南京研发小黑屋1";
    @ApiModelProperty(required = true)
    private String deviceSn;//"SXYTJYS20191126000037";
    @ApiModelProperty(required = true)
    private String callProtocol;//= "SVC";

    private String ip = "";
    private boolean available = true;
    private boolean pollEndpointList = false;
    private Integer sortIndex = 0;
    private String callNumber = null;
    private String sipUrl = "";
    private String sipPassword = "";
    private String deviceStatus = "ONLINE";
    private String description = "";
    private Integer callSpeed = 0;
    private Integer userId = 0;
    private String userName = null;
    private String platform = "PC";
    private String netType = "intranet";
    private String endpointType = "OTHER";
    private String cascadeRoleType = null;
    private String dialDirection = "Dial-out";
    private String e164 = "";
    private Integer agentUserId = null;
    private String agentUserDisplayName = "agentadmin";
    private Integer orgId = null;//22;
    private String orgName = "";//""会畅教育学院";
    private Integer deptId = 0;
    private String deptShortName = null;
    private String zoneName = "";
    private String gateway = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isPollEndpointList() {
        return pollEndpointList;
    }

    public void setPollEndpointList(boolean pollEndpointList) {
        this.pollEndpointList = pollEndpointList;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getCallProtocol() {
        return callProtocol;
    }

    public void setCallProtocol(String callProtocol) {
        this.callProtocol = callProtocol;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getSipUrl() {
        return sipUrl;
    }

    public void setSipUrl(String sipUrl) {
        this.sipUrl = sipUrl;
    }

    public String getSipPassword() {
        return sipPassword;
    }

    public void setSipPassword(String sipPassword) {
        this.sipPassword = sipPassword;
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

    public Integer getCallSpeed() {
        return callSpeed;
    }

    public void setCallSpeed(Integer callSpeed) {
        this.callSpeed = callSpeed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getEndpointType() {
        return endpointType;
    }

    public void setEndpointType(String endpointType) {
        this.endpointType = endpointType;
    }

    public String getCascadeRoleType() {
        return cascadeRoleType;
    }

    public void setCascadeRoleType(String cascadeRoleType) {
        this.cascadeRoleType = cascadeRoleType;
    }

    public String getDialDirection() {
        return dialDirection;
    }

    public void setDialDirection(String dialDirection) {
        this.dialDirection = dialDirection;
    }

    public String getE164() {
        return e164;
    }

    public void setE164(String e164) {
        this.e164 = e164;
    }

    public Integer getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(Integer agentUserId) {
        this.agentUserId = agentUserId;
    }

    public String getAgentUserDisplayName() {
        return agentUserDisplayName;
    }

    public void setAgentUserDisplayName(String agentUserDisplayName) {
        this.agentUserDisplayName = agentUserDisplayName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptShortName() {
        return deptShortName;
    }

    public void setDeptShortName(String deptShortName) {
        this.deptShortName = deptShortName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
}
