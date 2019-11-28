package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:42
 */
public class EndpointNode {
    private String type;
    private boolean dialIn;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDialIn() {
        return dialIn;
    }

    public void setDialIn(boolean dialIn) {
        this.dialIn = dialIn;
    }
}
