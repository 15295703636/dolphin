package org.cs.dp.ucenter.domain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerEntity {
    private Integer id;

    private String customer_id;

    private String customer_name;

    private Date customer_end_time;

    private String out_name;

    private String out_pwd;

    private String customer_address;

    private String remark;

    private Date create_time;

    private Date update_time;

    public CustomerEntity(Integer id, String customer_id, String customer_name, Date customer_end_time, String out_name, String out_pwd, String customer_address, String remark, Date create_time, Date update_time) {
        this.id = id;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_end_time = customer_end_time;
        this.out_name = out_name;
        this.out_pwd = out_pwd;
        this.customer_address = customer_address;
        this.remark = remark;
        this.create_time = create_time;
        this.update_time = update_time;
    }
}