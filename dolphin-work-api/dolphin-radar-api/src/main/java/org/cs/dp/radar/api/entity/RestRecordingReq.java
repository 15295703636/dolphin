package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:07
 */
public class RestRecordingReq {
    private String profile	;//	是	-	取值见常量：录制/直播类型，建议值为"GW_WEBCAST"
    private String peoplePlusContentLayoutMode	;//	是	-	取值见常量：双流分屏，建议值为"oneByOne".

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
