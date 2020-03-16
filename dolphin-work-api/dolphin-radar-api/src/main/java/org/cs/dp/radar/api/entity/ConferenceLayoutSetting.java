package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:48
 */
public class ConferenceLayoutSetting {

    private LayoutBaseAttribute baseAttribute	;//	-	-
    private List<ConferencePaneSetting> panesSetting	;//	-	-
    private String lectureName	;//	主会场名称	-
    private String contentTokenOwnerGuid	;//	双流令牌拥有者的Guid	-
    private int playbackMediaId	;//	-

    public LayoutBaseAttribute getBaseAttribute() {
        return baseAttribute;
    }

    public void setBaseAttribute(LayoutBaseAttribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }

    public List<ConferencePaneSetting> getPanesSetting() {
        return panesSetting;
    }

    public void setPanesSetting(List<ConferencePaneSetting> panesSetting) {
        this.panesSetting = panesSetting;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getContentTokenOwnerGuid() {
        return contentTokenOwnerGuid;
    }

    public void setContentTokenOwnerGuid(String contentTokenOwnerGuid) {
        this.contentTokenOwnerGuid = contentTokenOwnerGuid;
    }

    public int getPlaybackMediaId() {
        return playbackMediaId;
    }

    public void setPlaybackMediaId(int playbackMediaId) {
        this.playbackMediaId = playbackMediaId;
    }
}
