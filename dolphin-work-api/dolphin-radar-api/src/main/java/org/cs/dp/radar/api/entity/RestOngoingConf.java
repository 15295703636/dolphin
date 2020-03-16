package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:53
 */
public class RestOngoingConf {

    private BasicInfo basicInfo	;//	会议的基本信息	-
    private long startTime	;//	会议的开始时刻	以毫秒为单位的Unix时间戳
    private long endTime	;//	会议的结束时刻	以毫秒为单位的Unix时间戳
    private boolean forever	;//	是否为会议群组	-
    private String conferenceParameterName	;//	-	取值有：”SVC”
    private int callRate	;//	-	-
    private boolean encryption	;//	是否加密	-
    private boolean dba	;//	-	-
    private boolean autoMute	;//	是否开启了自动静音	-
    private TerminateInfo terminateInfo	;//	-	-
    private boolean enableStreaming	;//	-	-
    private boolean enableRecordingPlayback	;//	-	-
    private VideoQuality videoQuality	;//	-	-
    private ConferenceLayoutSetting layoutSetting	;//	-	-
    private SiteName siteName	;//	-	-
    private String recordingStatus	;//	-	-
    private String recordingUrl	;//	-	-
    private String liveStreamingStatus	;//	-	-
    private List<RestPartyMru> partyMrus	;//	-	-
    private RestUser applicant	;//	会议申请人	-
    private RestUser adminUser	;//	备选会控者	-
    private List<Long> hostUserIds	;//	拥有会控权限的所有用户的id	-
    private String lecturerGuid	;//	主会场的Guid	-
    private String hostZoneAddress	;//	会议的主可用区的地址	-
    private int maxBandwidth	;//	最大带宽	-
    private String profile	;//	-	-
    private String beingCalledPartyGuid	;//	正被呼叫的与会者的Guid	-
    private String beingBroadcastedPartyGuid	;//	正被广播的与会者的Guid	-
    private String beingScannedPartyGuid	;//	-	-
    private boolean enabledRedialing	;//	是否开启了自动重拨	-
    private boolean enabledRecording	;//	是否开启了录制	-
    private String recordingLayout	;//	-	-
    private String liveStreamingUrl	;//	推流地址	-
    private boolean enabledAutoLayout	;//	是否开启了自动分屏	-
    private boolean enabledSubtitle	;//	是否启用了字幕	-
    private boolean enabledLoopBroadcast	;//	-	-
    private boolean enabledLoopPartiesInPanes	;


    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public boolean isForever() {
        return forever;
    }

    public void setForever(boolean forever) {
        this.forever = forever;
    }

    public String getConferenceParameterName() {
        return conferenceParameterName;
    }

    public void setConferenceParameterName(String conferenceParameterName) {
        this.conferenceParameterName = conferenceParameterName;
    }

    public int getCallRate() {
        return callRate;
    }

    public void setCallRate(int callRate) {
        this.callRate = callRate;
    }

    public boolean isEncryption() {
        return encryption;
    }

    public void setEncryption(boolean encryption) {
        this.encryption = encryption;
    }

    public boolean isDba() {
        return dba;
    }

    public void setDba(boolean dba) {
        this.dba = dba;
    }

    public boolean isAutoMute() {
        return autoMute;
    }

    public void setAutoMute(boolean autoMute) {
        this.autoMute = autoMute;
    }

    public TerminateInfo getTerminateInfo() {
        return terminateInfo;
    }

    public void setTerminateInfo(TerminateInfo terminateInfo) {
        this.terminateInfo = terminateInfo;
    }

    public boolean isEnableStreaming() {
        return enableStreaming;
    }

    public void setEnableStreaming(boolean enableStreaming) {
        this.enableStreaming = enableStreaming;
    }

    public boolean isEnableRecordingPlayback() {
        return enableRecordingPlayback;
    }

    public void setEnableRecordingPlayback(boolean enableRecordingPlayback) {
        this.enableRecordingPlayback = enableRecordingPlayback;
    }

    public VideoQuality getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(VideoQuality videoQuality) {
        this.videoQuality = videoQuality;
    }

    public ConferenceLayoutSetting getLayoutSetting() {
        return layoutSetting;
    }

    public void setLayoutSetting(ConferenceLayoutSetting layoutSetting) {
        this.layoutSetting = layoutSetting;
    }

    public SiteName getSiteName() {
        return siteName;
    }

    public void setSiteName(SiteName siteName) {
        this.siteName = siteName;
    }

    public String getRecordingStatus() {
        return recordingStatus;
    }

    public void setRecordingStatus(String recordingStatus) {
        this.recordingStatus = recordingStatus;
    }

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }

    public String getLiveStreamingStatus() {
        return liveStreamingStatus;
    }

    public void setLiveStreamingStatus(String liveStreamingStatus) {
        this.liveStreamingStatus = liveStreamingStatus;
    }

    public List<RestPartyMru> getPartyMrus() {
        return partyMrus;
    }

    public void setPartyMrus(List<RestPartyMru> partyMrus) {
        this.partyMrus = partyMrus;
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

    public List<Long> getHostUserIds() {
        return hostUserIds;
    }

    public void setHostUserIds(List<Long> hostUserIds) {
        this.hostUserIds = hostUserIds;
    }

    public String getLecturerGuid() {
        return lecturerGuid;
    }

    public void setLecturerGuid(String lecturerGuid) {
        this.lecturerGuid = lecturerGuid;
    }

    public String getHostZoneAddress() {
        return hostZoneAddress;
    }

    public void setHostZoneAddress(String hostZoneAddress) {
        this.hostZoneAddress = hostZoneAddress;
    }

    public int getMaxBandwidth() {
        return maxBandwidth;
    }

    public void setMaxBandwidth(int maxBandwidth) {
        this.maxBandwidth = maxBandwidth;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBeingCalledPartyGuid() {
        return beingCalledPartyGuid;
    }

    public void setBeingCalledPartyGuid(String beingCalledPartyGuid) {
        this.beingCalledPartyGuid = beingCalledPartyGuid;
    }

    public String getBeingBroadcastedPartyGuid() {
        return beingBroadcastedPartyGuid;
    }

    public void setBeingBroadcastedPartyGuid(String beingBroadcastedPartyGuid) {
        this.beingBroadcastedPartyGuid = beingBroadcastedPartyGuid;
    }

    public String getBeingScannedPartyGuid() {
        return beingScannedPartyGuid;
    }

    public void setBeingScannedPartyGuid(String beingScannedPartyGuid) {
        this.beingScannedPartyGuid = beingScannedPartyGuid;
    }

    public boolean isEnabledRedialing() {
        return enabledRedialing;
    }

    public void setEnabledRedialing(boolean enabledRedialing) {
        this.enabledRedialing = enabledRedialing;
    }

    public boolean isEnabledRecording() {
        return enabledRecording;
    }

    public void setEnabledRecording(boolean enabledRecording) {
        this.enabledRecording = enabledRecording;
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

    public boolean isEnabledAutoLayout() {
        return enabledAutoLayout;
    }

    public void setEnabledAutoLayout(boolean enabledAutoLayout) {
        this.enabledAutoLayout = enabledAutoLayout;
    }

    public boolean isEnabledSubtitle() {
        return enabledSubtitle;
    }

    public void setEnabledSubtitle(boolean enabledSubtitle) {
        this.enabledSubtitle = enabledSubtitle;
    }

    public boolean isEnabledLoopBroadcast() {
        return enabledLoopBroadcast;
    }

    public void setEnabledLoopBroadcast(boolean enabledLoopBroadcast) {
        this.enabledLoopBroadcast = enabledLoopBroadcast;
    }

    public boolean isEnabledLoopPartiesInPanes() {
        return enabledLoopPartiesInPanes;
    }

    public void setEnabledLoopPartiesInPanes(boolean enabledLoopPartiesInPanes) {
        this.enabledLoopPartiesInPanes = enabledLoopPartiesInPanes;
    }
}
