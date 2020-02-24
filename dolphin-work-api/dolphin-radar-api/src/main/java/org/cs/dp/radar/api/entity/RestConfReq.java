package org.cs.dp.radar.api.entity;

import java.util.List;

public class RestConfReq {
    private String name;
    //会议的开始时刻（以毫秒为单位的Unix时间戳），0表示该会议立即开始
    private Long startTime;
    //会议的持续时长（单位为毫秒）,建议值为3600000，即一个小时
    private Long duration;
    //被设置为主会场的某个终端的id
    private Long lecturerEpId;
    //被设置为主会场的某个用户的id
    private Long lecturerUserId;
    //取值见常量：会议的分屏模式
    private String layout;
    //会议质量，"SVC"表示质量优先，"SVC_ECO"表示带宽优先，建议值为"SVC".
    private String profile;
    //	会议密码
    private String password;
    //会议备注
    private String description;
    //与会终端id的列表
    private List<Long> endpointIds;
    //与会用户id的列表
    private List<Long> userIds;
    //指定的云会议室的id
    private Long roomId;
    //最大入会方数
    private int maxPartyCount;
    //使用随机号码作为会议号码
    private boolean useRandomNumericId;
    //自动重拨
    private boolean redialingEnabled;
    //录制
    private boolean recordingEnabled;
    //取值见常量：录制/直播类型,建议值为"GW_WEBCAST".
    private String recordingProfile;
    //取值见常量：双流分屏，建议值为"oneByOne".
    private String recordingLayout;
    //推流地址
    private String liveStreamingUrl;
    //备选会控者的id
    private Long adminUserId;
    //字幕
    private RestSubtitle subtitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getLecturerEpId() {
        return lecturerEpId;
    }

    public void setLecturerEpId(Long lecturerEpId) {
        this.lecturerEpId = lecturerEpId;
    }

    public Long getLecturerUserId() {
        return lecturerUserId;
    }

    public void setLecturerUserId(Long lecturerUserId) {
        this.lecturerUserId = lecturerUserId;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getEndpointIds() {
        return endpointIds;
    }

    public void setEndpointIds(List<Long> endpointIds) {
        this.endpointIds = endpointIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getMaxPartyCount() {
        return maxPartyCount;
    }

    public void setMaxPartyCount(int maxPartyCount) {
        this.maxPartyCount = maxPartyCount;
    }

    public boolean isUseRandomNumericId() {
        return useRandomNumericId;
    }

    public void setUseRandomNumericId(boolean useRandomNumericId) {
        this.useRandomNumericId = useRandomNumericId;
    }

    public boolean isRedialingEnabled() {
        return redialingEnabled;
    }

    public void setRedialingEnabled(boolean redialingEnabled) {
        this.redialingEnabled = redialingEnabled;
    }

    public boolean isRecordingEnabled() {
        return recordingEnabled;
    }

    public void setRecordingEnabled(boolean recordingEnabled) {
        this.recordingEnabled = recordingEnabled;
    }

    public String getRecordingProfile() {
        return recordingProfile;
    }

    public void setRecordingProfile(String recordingProfile) {
        this.recordingProfile = recordingProfile;
    }

    public String getRecordingLayout() {
        return recordingLayout;
    }

    public void setRecordingLayout(String recordingLayout) {
        this.recordingLayout = recordingLayout;
    }

    public String getLiveStreamingUrl() {
        return liveStreamingUrl;
    }

    public void setLiveStreamingUrl(String liveStreamingUrl) {
        this.liveStreamingUrl = liveStreamingUrl;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public RestSubtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(RestSubtitle subtitle) {
        this.subtitle = subtitle;
    }
}
