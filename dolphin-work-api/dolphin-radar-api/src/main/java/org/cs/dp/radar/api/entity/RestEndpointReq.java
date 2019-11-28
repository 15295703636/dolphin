package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:28
 */
public class RestEndpointReq {
    private long id;//	否	-	终端的ID	修改终端时必须赋值
    private String systemName;//	是	-	名称	-
    private String ip;//	否	-	IP地址	-
    private String callProtocol;//	是	-	呼叫协议	取值见常量：呼叫协议
    private String sipUrl;//	否	-	SIP号码	callProtocol的值为“SIP”时，需要为本字段和ip字段中的至少一项赋值
    private String description;//	否	-	对终端的描述	-
    private String deviceSn;//	否	-	终端的序列号	callProtocol的值为“SVC”时，必须为本字段赋值
    private String endpointType;//	是	-	终端类型	取值见常量：终端类型，不能赋值为“HEXMEET”
    private String e164;//	否	-	E.164号	callProtocol的值为“H.323”时，需要为本字段和ip字段中的至少一项赋值
    private long deptId;//	否	-	终端所属部门的id	-
    private String zoneName;//	否	-	终端所属可用区的名称	callProtocol的值为“SIP”或“H.323”时，必须为本字段赋值

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
}
