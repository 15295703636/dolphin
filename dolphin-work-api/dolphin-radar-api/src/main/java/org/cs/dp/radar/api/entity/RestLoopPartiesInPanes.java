package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:09
 */
public class RestLoopPartiesInPanes {
    private String layoutType	;//	是	-	见常量：会议的分屏模式
    private long intervalInSeconds	;//	是	-	轮询间隔，单位为秒，最小值为10
    private List<RestPane> panes	;//	是	-	窗口列表

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

    public List<RestPane> getPanes() {
        return panes;
    }

    public void setPanes(List<RestPane> panes) {
        this.panes = panes;
    }
}
