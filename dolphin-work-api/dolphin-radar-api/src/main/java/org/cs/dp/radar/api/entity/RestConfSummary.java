package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:58
 */
public class RestConfSummary {

    private long id	;//	会议ID	-
    private String name	;//	会议名称	-
    private long startTime	;////	会议的开始时刻	以毫秒为单位的Unix时间戳
    private long duration	;////	会议的时长	以毫秒为单位
    private long createTime	;////	会议的创建时刻	以毫秒为单位的Unix时间戳
    private String status	;//	会议的状态	-
    private String numericId	;//	会议号	-
    private String guid	;//	-	-
    private RestUser applicant	;//	会议的申请人	-
    private RestUser adminUser	;//	会议的备选会控者	-
    private int maxBandwidth	;//最大带宽	-
    private long lecturerEpId	;//	作为主会场的终端的ID	-
    private String LecturerEpName	;//	作为主会场的终端的名称	-
    private boolean redialingEnabled	;//	是否开启了自动重拨	-
    private boolean recordingEnabled	;//	是否开启了录制	-
    private String layout	;//	分屏设置	-
    private String profile	;//	-	-
    private String password	;//	会议的密码	-
    private String description	;//	会议的备注	-
    private List<Long> endpointIds	;//	会议邀请的所有终端的ID	-
    private List<Long> userIds	;//	会议邀请的所有用户的ID	-
    private RestRoom room	;//	会议使用的会议室	-
    private int maxPartyCount	;//	最大入会方数	-
    private long version	;//	此条会议数据的版本号	-
    private boolean aliveInMru	;//	此会议是否存在于MRU	-
    private String softEndpointJoinUrl	;//	软终端的入会链接	-
    private String hostZoneAddress	;//	会议的主可用区的地址	-

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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public int getMaxBandwidth() {
        return maxBandwidth;
    }

    public void setMaxBandwidth(int maxBandwidth) {
        this.maxBandwidth = maxBandwidth;
    }

    public long getLecturerEpId() {
        return lecturerEpId;
    }

    public void setLecturerEpId(long lecturerEpId) {
        this.lecturerEpId = lecturerEpId;
    }

    public String getLecturerEpName() {
        return LecturerEpName;
    }

    public void setLecturerEpName(String lecturerEpName) {
        LecturerEpName = lecturerEpName;
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

    public RestRoom getRoom() {
        return room;
    }

    public void setRoom(RestRoom room) {
        this.room = room;
    }

    public int getMaxPartyCount() {
        return maxPartyCount;
    }

    public void setMaxPartyCount(int maxPartyCount) {
        this.maxPartyCount = maxPartyCount;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isAliveInMru() {
        return aliveInMru;
    }

    public void setAliveInMru(boolean aliveInMru) {
        this.aliveInMru = aliveInMru;
    }

    public String getSoftEndpointJoinUrl() {
        return softEndpointJoinUrl;
    }

    public void setSoftEndpointJoinUrl(String softEndpointJoinUrl) {
        this.softEndpointJoinUrl = softEndpointJoinUrl;
    }

    public String getHostZoneAddress() {
        return hostZoneAddress;
    }

    public void setHostZoneAddress(String hostZoneAddress) {
        this.hostZoneAddress = hostZoneAddress;
    }
}
