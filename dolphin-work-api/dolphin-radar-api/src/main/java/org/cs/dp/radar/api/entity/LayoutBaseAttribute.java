package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:46
 */
public class LayoutBaseAttribute {
    private int autoScanInterval	;
    private String conferenceMode;
    private String layoutType	;
    private String maxAutoLayoutType;

    public int getAutoScanInterval() {
        return autoScanInterval;
    }

    public void setAutoScanInterval(int autoScanInterval) {
        this.autoScanInterval = autoScanInterval;
    }

    public String getConferenceMode() {
        return conferenceMode;
    }

    public void setConferenceMode(String conferenceMode) {
        this.conferenceMode = conferenceMode;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public String getMaxAutoLayoutType() {
        return maxAutoLayoutType;
    }

    public void setMaxAutoLayoutType(String maxAutoLayoutType) {
        this.maxAutoLayoutType = maxAutoLayoutType;
    }
}
