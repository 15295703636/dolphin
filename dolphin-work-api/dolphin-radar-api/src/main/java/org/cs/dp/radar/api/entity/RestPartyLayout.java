package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:12
 */
public class RestPartyLayout {

    private int autoScanInterval = 3;//	是	-	暂不启用，请填写固定值3.
    private String layoutType;//	是	-	见常量：会议的分屏模式
    private List<Long> deviceIds;//	是	-	各个窗口要显示的与会者的deviceId的汇总列表。若某窗口对应的值为"auto"，则该窗口显示的与会者由系统自动设定。
    private Boolean isPersonal = true;//	是	-	当会议分屏被改变时，如果此字段的值为true，则不会影响此分屏设置，如果值为false，则会被新的会议分屏覆盖。
    //private Boolean padNull = true;//	是	-	对于未指定要显示的与会者的窗口，是否不显示任何内容

    public int getAutoScanInterval() {
        return autoScanInterval;
    }

    public void setAutoScanInterval(int autoScanInterval) {
        this.autoScanInterval = autoScanInterval;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public List<Long> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<Long> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public Boolean getPersonal() {
        return isPersonal;
    }

    public void setPersonal(Boolean personal) {
        isPersonal = personal;
    }

    /*public Boolean getPadNull() {
        return padNull;
    }

    public void setPadNull(Boolean padNull) {
        this.padNull = padNull;
    }*/
}
