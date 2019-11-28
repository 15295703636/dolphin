package org.cs.dp.radar.api.entity;

import java.util.Map;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:20
 */
public class RestAgentUserLicense {

    private  long startTime;		    //开始时刻（以毫秒为单位的Unix时间戳）
    private  long duration;		    //时长，以毫秒为单位
    private  Map<Integer, Integer> roomLimit;		//各种容量的会议室分别授予了多少个
    private  int gatewayPortCount;	//网关最大连接数
    private  int maxUserCount;	    //	最大用户数
    private  int maxPSTNCallCount;   //	PSTN并发数
    private  int	maxWebcastCount;	//直播并发数	暂未启用
    private  int maxStorageGbytes	;//最大文件存储空间，单位为G

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

    public Map<Integer, Integer> getRoomLimit() {
        return roomLimit;
    }

    public void setRoomLimit(Map<Integer, Integer> roomLimit) {
        this.roomLimit = roomLimit;
    }

    public int getGatewayPortCount() {
        return gatewayPortCount;
    }

    public void setGatewayPortCount(int gatewayPortCount) {
        this.gatewayPortCount = gatewayPortCount;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public int getMaxPSTNCallCount() {
        return maxPSTNCallCount;
    }

    public void setMaxPSTNCallCount(int maxPSTNCallCount) {
        this.maxPSTNCallCount = maxPSTNCallCount;
    }

    public int getMaxWebcastCount() {
        return maxWebcastCount;
    }

    public void setMaxWebcastCount(int maxWebcastCount) {
        this.maxWebcastCount = maxWebcastCount;
    }

    public int getMaxStorageGbytes() {
        return maxStorageGbytes;
    }

    public void setMaxStorageGbytes(int maxStorageGbytes) {
        this.maxStorageGbytes = maxStorageGbytes;
    }
}
