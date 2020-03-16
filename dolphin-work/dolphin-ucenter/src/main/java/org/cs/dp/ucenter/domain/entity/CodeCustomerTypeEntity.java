package org.cs.dp.ucenter.domain.entity;

import lombok.Data;

@Data
public class CodeCustomerTypeEntity  {
    private Integer id;
    private String name;

    public CodeCustomerTypeEntity() {
    }

    public CodeCustomerTypeEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}