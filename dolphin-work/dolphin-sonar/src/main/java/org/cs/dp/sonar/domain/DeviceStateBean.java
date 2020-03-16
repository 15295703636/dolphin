package org.cs.dp.sonar.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DeviceStateBean
 * @Description
 * @Author Liujt
 * @Date 2020/3/5 14:00
 **/
@Data
public class DeviceStateBean implements Serializable {
    private Long date;
    //0：离线
    //1：空闲
    //2：空闲（仅可录制）
    //3：忙碌
    private Integer status;
    private String sn;

    public DeviceStateBean() {
    }

    public DeviceStateBean(Long date, Integer status) {
        this.date = date;
        this.status = status;
    }
}
