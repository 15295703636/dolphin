package org.cs.dp.ucenter.domain.entity;

import lombok.Data;

@Data
public class UserOrgEntity {
    private Integer user2org_id;

    private Integer org_id;

    private Integer user_id;

    public UserOrgEntity() {
    }

    public UserOrgEntity(Integer user2org_id, Integer org_id, Integer user_id) {
        this.user2org_id = user2org_id;
        this.org_id = org_id;
        this.user_id = user_id;
    }
}