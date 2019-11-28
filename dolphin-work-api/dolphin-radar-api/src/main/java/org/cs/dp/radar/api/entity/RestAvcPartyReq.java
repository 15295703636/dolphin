package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:35
 */
public class RestAvcPartyReq {
    private String endpointName	;//	是	名称
    private String profile	;//	是	呼叫质量，"GW_HD"表示高清，"GW_FHD"表示超清。
    private String protocol	;//	是	呼叫协议，可取的值有："H323"，“SIP"。
    private String sipUrl	;//	否	SIP号码；sipUrl,e164,endpointIp这三个字段必须且只能选择一个赋值
    private String e164	;//	否	E.164号
    private String endpointIp	;//	否	IP地址

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSipUrl() {
        return sipUrl;
    }

    public void setSipUrl(String sipUrl) {
        this.sipUrl = sipUrl;
    }

    public String getE164() {
        return e164;
    }

    public void setE164(String e164) {
        this.e164 = e164;
    }

    public String getEndpointIp() {
        return endpointIp;
    }

    public void setEndpointIp(String endpointIp) {
        this.endpointIp = endpointIp;
    }
}
