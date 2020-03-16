package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:51
 */
public class PeopleVideoQuality {
    private String videoSetting	;//	视频设置，取值为：h264Cif4，h264720p，h2641080p，hdSwitching	-
    private int frameRate	;//	帧率，取值为：25，30，50，60

    public String getVideoSetting() {
        return videoSetting;
    }

    public void setVideoSetting(String videoSetting) {
        this.videoSetting = videoSetting;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
}
