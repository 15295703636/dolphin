package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:31
 */
public class RestFeatureSupport {
    private boolean contactWebPage	;//	是否支持通讯录	-
    private boolean p2pCall	;//	是否支持直接呼叫终端或用户	-
    private boolean chatInConference	;//	是否支持会议中的聊天	-
    private boolean switchingToAudioConference	;//	是否支持会议中切换为纯音频模式	-
    private boolean sitenameIsChangeable	;//	是否允许客户端改会场名

    public boolean isContactWebPage() {
        return contactWebPage;
    }

    public void setContactWebPage(boolean contactWebPage) {
        this.contactWebPage = contactWebPage;
    }

    public boolean isP2pCall() {
        return p2pCall;
    }

    public void setP2pCall(boolean p2pCall) {
        this.p2pCall = p2pCall;
    }

    public boolean isChatInConference() {
        return chatInConference;
    }

    public void setChatInConference(boolean chatInConference) {
        this.chatInConference = chatInConference;
    }

    public boolean isSwitchingToAudioConference() {
        return switchingToAudioConference;
    }

    public void setSwitchingToAudioConference(boolean switchingToAudioConference) {
        this.switchingToAudioConference = switchingToAudioConference;
    }

    public boolean isSitenameIsChangeable() {
        return sitenameIsChangeable;
    }

    public void setSitenameIsChangeable(boolean sitenameIsChangeable) {
        this.sitenameIsChangeable = sitenameIsChangeable;
    }
}
