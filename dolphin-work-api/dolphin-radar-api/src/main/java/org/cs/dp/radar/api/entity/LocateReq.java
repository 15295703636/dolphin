package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:04
 */
public class LocateReq {
    private String guid	;//此次定位请求的ID，16进制的8位数	每次请求需要用不同的guid.取值举例: "CAA12AD6".

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
