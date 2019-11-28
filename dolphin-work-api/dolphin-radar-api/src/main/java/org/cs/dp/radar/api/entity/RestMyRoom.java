package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:26
 */
public class RestMyRoom {
    private String name	;//	会议室名称	-
    private int capacity	;//	会议室容量	-
    private String numericId	;//	会议室号码	-
    private RestMyRoomConfigDisplay roomConfigDisplay	;//	会议室的设置	-

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getNumericId() {
        return numericId;
    }

    public void setNumericId(String numericId) {
        this.numericId = numericId;
    }

    public RestMyRoomConfigDisplay getRoomConfigDisplay() {
        return roomConfigDisplay;
    }

    public void setRoomConfigDisplay(RestMyRoomConfigDisplay roomConfigDisplay) {
        this.roomConfigDisplay = roomConfigDisplay;
    }
}
