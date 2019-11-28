package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:07
 */
public class RestPane {
    private long index	;//	是	-	窗口的序号，从1开始
    private boolean fixed	;//	是	-	该窗口是否固定显示某个与会者
    private List<Long> deviceIds	;//	是	-	该窗口要显示的与会者的deviceId的列表

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }
}
