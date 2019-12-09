package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class InteractionDeviceEntity {
    private Integer id;

    private Integer interaction_id;

    private Integer device_id;

    public InteractionDeviceEntity() {
    }

    public InteractionDeviceEntity(Integer interaction_id, Integer device_id) {
        this.interaction_id = interaction_id;
        this.device_id = device_id;
    }

    public InteractionDeviceEntity(Integer id, Integer interaction_id, Integer device_id) {
        this.id = id;
        this.interaction_id = interaction_id;
        this.device_id = device_id;
    }

}