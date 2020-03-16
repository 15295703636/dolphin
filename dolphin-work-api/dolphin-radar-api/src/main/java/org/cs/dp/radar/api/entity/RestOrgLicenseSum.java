package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:16
 */
public class RestOrgLicenseSum {

    private int gatewayPortCount;
    private int maxUserCount;
    private int maxPSTNCallCount;
    private int maxWebcastCount;
    private int maxStorageGbytes;
    private int roomCount;
    private long roomCapacity;
    private boolean trial;

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

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public long getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(long roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public boolean isTrial() {
        return trial;
    }

    public void setTrial(boolean trial) {
        this.trial = trial;
    }
}
