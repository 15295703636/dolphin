package org.cs.dp.ucenter.domain.entity;

public class CodeEntity {
    private Integer code_id;

    private String code_name;

    private String table_name;

    private String remark;

    private Integer enable_sign;

    public CodeEntity(Integer code_id, String code_name, String table_name, String remark, Integer enable_sign) {
        this.code_id = code_id;
        this.code_name = code_name;
        this.table_name = table_name;
        this.remark = remark;
        this.enable_sign = enable_sign;
    }

    public Integer getCode_id() {
        return code_id;
    }

    public String getCode_name() {
        return code_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getEnable_sign() {
        return enable_sign;
    }
}