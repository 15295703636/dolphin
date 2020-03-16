package org.cs.dp.radar.api.entity;

import java.util.List;

public class RestConf {
    private long id;
    private String name;
    private long startTime;
    private long duration;
    private long createTime;
    private String guid;
    private String status;
    private String numericId;
    private String hostZoneAddress;
    private RestUser applicant;
    private RestUser adminUser;
    private long lecturerEpId;
    private String lecturerEpName;
    private long lecturerUserId;
    private String layout;
    private String profile;
    private String password;
    private String description;
    private List<RestEndpoint> endpoints;
    private List<RestUser> users;
    private RestRoom confRoom;
    private int maxPartyCount;
    private boolean useRandomNumericId;
    private boolean redialingEnabled;
    private boolean recordingEnabled;
    private String recordingProfile;
    private String recordingLayout;
    private String liveStreamingUrl;
    private RestSubtitle subtitle;
    private int inCallPartyCount;
    private int version;

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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumericId() {
        return numericId;
    }

    public void setNumericId(String numericId) {
        this.numericId = numericId;
    }

    public String getHostZoneAddress() {
        return hostZoneAddress;
    }

    public void setHostZoneAddress(String hostZoneAddress) {
        this.hostZoneAddress = hostZoneAddress;
    }

    public RestUser getApplicant() {
        return applicant;
    }

    public void setApplicant(RestUser applicant) {
        this.applicant = applicant;
    }

    public RestUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(RestUser adminUser) {
        this.adminUser = adminUser;
    }

    public long getLecturerEpId() {
        return lecturerEpId;
    }

    public void setLecturerEpId(long lecturerEpId) {
        this.lecturerEpId = lecturerEpId;
    }

    public String getLecturerEpName() {
        return lecturerEpName;
    }

    public void setLecturerEpName(String lecturerEpName) {
        this.lecturerEpName = lecturerEpName;
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

    public List<RestEndpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<RestEndpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public List<RestUser> getUsers() {
        return users;
    }

    public void setUsers(List<RestUser> users) {
        this.users = users;
    }

    public RestRoom getConfRoom() {
        return confRoom;
    }

    public void setConfRoom(RestRoom confRoom) {
        this.confRoom = confRoom;
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

    public RestSubtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(RestSubtitle subtitle) {
        this.subtitle = subtitle;
    }

    public int getInCallPartyCount() {
        return inCallPartyCount;
    }

    public void setInCallPartyCount(int inCallPartyCount) {
        this.inCallPartyCount = inCallPartyCount;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
