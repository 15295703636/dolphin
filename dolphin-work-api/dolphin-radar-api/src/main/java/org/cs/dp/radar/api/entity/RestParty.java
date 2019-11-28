package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:37
 */
public class RestParty {
    private String guid	;//	ID	-
    private String name	;//	名称	-
    private String ipV4	;//	IP地址	-
    private EndpointAlias endpointAlias	;//	别名	-
    private int callRate	;//	呼叫速率	-
    private boolean audioOnly	;//
    private EndpointNode node	;//	-	-
    private boolean focus	;//	是否为焦点	-
    private boolean feccTokenOwner	;//	-	-
    private boolean speaker	;//	-	-
    private boolean streaming	;//	-	-
    private boolean empoweredContentToken	;//	是否被授予了双流令牌	-
    private boolean encryption	;//	是否加密	-
    private String connectionStatus	;//	连接状态	取值有：”connected”,”disconnected”
    private String disconnectedCause	;//	连接断开的原因	取值有：”disconnectedNormally”
    private boolean dialIn	;//	是否为呼入	-
    private boolean audioBlocked	;//	是否已被屏蔽音频	-
    private boolean audioMuted	;//	是否已被静音	-
    private boolean endpointAudioMuted	;//	是否已被终端本地静音	-
    private boolean videoSuspended	;//	-	-
    private boolean videoFaulty	;//	-	-
    private boolean requestFloor	;//	-	-
    private long requestFloorTime	;//	-	以毫秒为单位的Unix时间戳
    private boolean approvedFloor	;//	-	-
    private boolean handsUp	;//	是否已举手	-
    private String deviceId	;//	终端的ID	-
    private boolean fromGateway	;//	-	-
    private boolean recorder	;//	-	-
    private boolean monitor	;//	是否为旁观 者终端	-
    private String gatewayIp	;//	-	-
    private long userId	;//	对应的用户的ID	-
    private String UserType	;//	对应的用户的类型	-
    private String endpointType	;//	终端类型	-

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpV4() {
        return ipV4;
    }

    public void setIpV4(String ipV4) {
        this.ipV4 = ipV4;
    }

    public EndpointAlias getEndpointAlias() {
        return endpointAlias;
    }

    public void setEndpointAlias(EndpointAlias endpointAlias) {
        this.endpointAlias = endpointAlias;
    }

    public int getCallRate() {
        return callRate;
    }

    public void setCallRate(int callRate) {
        this.callRate = callRate;
    }

    public boolean isAudioOnly() {
        return audioOnly;
    }

    public void setAudioOnly(boolean audioOnly) {
        this.audioOnly = audioOnly;
    }

    public EndpointNode getNode() {
        return node;
    }

    public void setNode(EndpointNode node) {
        this.node = node;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public boolean isFeccTokenOwner() {
        return feccTokenOwner;
    }

    public void setFeccTokenOwner(boolean feccTokenOwner) {
        this.feccTokenOwner = feccTokenOwner;
    }

    public boolean isSpeaker() {
        return speaker;
    }

    public void setSpeaker(boolean speaker) {
        this.speaker = speaker;
    }

    public boolean isStreaming() {
        return streaming;
    }

    public void setStreaming(boolean streaming) {
        this.streaming = streaming;
    }

    public boolean isEmpoweredContentToken() {
        return empoweredContentToken;
    }

    public void setEmpoweredContentToken(boolean empoweredContentToken) {
        this.empoweredContentToken = empoweredContentToken;
    }

    public boolean isEncryption() {
        return encryption;
    }

    public void setEncryption(boolean encryption) {
        this.encryption = encryption;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getDisconnectedCause() {
        return disconnectedCause;
    }

    public void setDisconnectedCause(String disconnectedCause) {
        this.disconnectedCause = disconnectedCause;
    }

    public boolean isDialIn() {
        return dialIn;
    }

    public void setDialIn(boolean dialIn) {
        this.dialIn = dialIn;
    }

    public boolean isAudioBlocked() {
        return audioBlocked;
    }

    public void setAudioBlocked(boolean audioBlocked) {
        this.audioBlocked = audioBlocked;
    }

    public boolean isAudioMuted() {
        return audioMuted;
    }

    public void setAudioMuted(boolean audioMuted) {
        this.audioMuted = audioMuted;
    }

    public boolean isEndpointAudioMuted() {
        return endpointAudioMuted;
    }

    public void setEndpointAudioMuted(boolean endpointAudioMuted) {
        this.endpointAudioMuted = endpointAudioMuted;
    }

    public boolean isVideoSuspended() {
        return videoSuspended;
    }

    public void setVideoSuspended(boolean videoSuspended) {
        this.videoSuspended = videoSuspended;
    }

    public boolean isVideoFaulty() {
        return videoFaulty;
    }

    public void setVideoFaulty(boolean videoFaulty) {
        this.videoFaulty = videoFaulty;
    }

    public boolean isRequestFloor() {
        return requestFloor;
    }

    public void setRequestFloor(boolean requestFloor) {
        this.requestFloor = requestFloor;
    }

    public long getRequestFloorTime() {
        return requestFloorTime;
    }

    public void setRequestFloorTime(long requestFloorTime) {
        this.requestFloorTime = requestFloorTime;
    }

    public boolean isApprovedFloor() {
        return approvedFloor;
    }

    public void setApprovedFloor(boolean approvedFloor) {
        this.approvedFloor = approvedFloor;
    }

    public boolean isHandsUp() {
        return handsUp;
    }

    public void setHandsUp(boolean handsUp) {
        this.handsUp = handsUp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isFromGateway() {
        return fromGateway;
    }

    public void setFromGateway(boolean fromGateway) {
        this.fromGateway = fromGateway;
    }

    public boolean isRecorder() {
        return recorder;
    }

    public void setRecorder(boolean recorder) {
        this.recorder = recorder;
    }

    public boolean isMonitor() {
        return monitor;
    }

    public void setMonitor(boolean monitor) {
        this.monitor = monitor;
    }

    public String getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(String gatewayIp) {
        this.gatewayIp = gatewayIp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getEndpointType() {
        return endpointType;
    }

    public void setEndpointType(String endpointType) {
        this.endpointType = endpointType;
    }
}
