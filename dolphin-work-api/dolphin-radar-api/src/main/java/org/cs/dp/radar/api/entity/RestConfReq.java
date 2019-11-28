package org.cs.dp.radar.api.entity;

import java.util.List;

public class RestConfReq {
    private String name;
    private long startTime;
    private long duration;
    private long lecturerEpId;
    private long lecturerUserId;
    private String layout;
    private String profile;
    private String password;
    private String description;
    private List<Long> endpointIds;
    private List<Long> userIds;
    private long roomId;
    private int maxPartyCount;
    private boolean useRandomNumericId;
    private boolean redialingEnabled;
    private boolean recordingEnabled;
    private String recordingProfile;
    private String recordingLayout;
    private String liveStreamingUrl;
    private long adminUserId;
    private RestSubtitle subtitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getLecturerEpId() {
        return lecturerEpId;
    }

    public void setLecturerEpId(long lecturerEpId) {
        this.lecturerEpId = lecturerEpId;
    }

    public long getLecturerUserId() {
        return lecturerUserId;
    }

    public void setLecturerUserId(long lecturerUserId) {
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

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
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

    public long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public RestSubtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(RestSubtitle subtitle) {
        this.subtitle = subtitle;
    }
}
