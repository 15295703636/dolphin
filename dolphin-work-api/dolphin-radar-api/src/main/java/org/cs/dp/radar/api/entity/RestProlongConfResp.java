package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:13
 */
public class RestProlongConfResp {
    private long newEndTime	;//	新的会议结束时间	以毫秒为单位的Unix时间戳
    private long closestStartTime	;//	与本会议在同一会议室的下一个会议的开始时间，即延时的上限	如果按要求的时长延时后，与下一个会议的时间有冲突，那么会退而延时至下一个会议的开始时间。如果不存在冲突，该字段的值为0.

    public long getNewEndTime() {
        return newEndTime;
    }

    public void setNewEndTime(long newEndTime) {
        this.newEndTime = newEndTime;
    }

    public long getClosestStartTime() {
        return closestStartTime;
    }

    public void setClosestStartTime(long closestStartTime) {
        this.closestStartTime = closestStartTime;
    }
}
