package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:06
 */
public class RestLiveStreamingReq {

    private String url	;//	是	-	推流地址
    private boolean record	;//	是	-	是否录制
    private String profile	;//	是	-	取值见常量：录制/直播类型，建议值为"GW_WEBCAST"
    private String peoplePlusContentLayoutMode	;//	是	-	取值见常量：双流分屏，建议值为"oneByOne".

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRecord() {
        return record;
    }

    public void setRecord(boolean record) {
        this.record = record;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPeoplePlusContentLayoutMode() {
        return peoplePlusContentLayoutMode;
    }

    public void setPeoplePlusContentLayoutMode(String peoplePlusContentLayoutMode) {
        this.peoplePlusContentLayoutMode = peoplePlusContentLayoutMode;
    }
}
