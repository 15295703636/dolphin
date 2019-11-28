package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:10
 */
public class RestLoopBroadcast {
    private long intervalInSeconds	;//	是	-	轮询间隔，单位为秒，最小值为10
    private List<Long> deviceIds	;//	是	-	参与广播轮询的与会者的deviceId的列表

    public long getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(long intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }
}
