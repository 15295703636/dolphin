package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:52
 */
public class ContentVideoQuality {
    private String videoCapability	;//	视频最大能力，取值为：upToH264，upToH2641080p5，upToH2641080p15，upToH2641080p30，upToH2641080p60	-
    private String qualitySetting	;//	质量设置，取值为：normalGraphics，hiresGraphics，liveVideo	-
    private boolean enableContentTranscodingForBadNetwork	;//	是否启用智能双流转码

    public String getVideoCapability() {
        return videoCapability;
    }

    public void setVideoCapability(String videoCapability) {
        this.videoCapability = videoCapability;
    }

    public String getQualitySetting() {
        return qualitySetting;
    }

    public void setQualitySetting(String qualitySetting) {
        this.qualitySetting = qualitySetting;
    }

    public boolean isEnableContentTranscodingForBadNetwork() {
        return enableContentTranscodingForBadNetwork;
    }

    public void setEnableContentTranscodingForBadNetwork(boolean enableContentTranscodingForBadNetwork) {
        this.enableContentTranscodingForBadNetwork = enableContentTranscodingForBadNetwork;
    }
}
