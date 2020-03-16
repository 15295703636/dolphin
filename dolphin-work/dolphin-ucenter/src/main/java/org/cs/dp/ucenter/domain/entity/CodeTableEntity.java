package org.cs.dp.ucenter.domain.entity;

public class CodeTableEntity {
    private String table_name;

    private String table_explain;

    public CodeTableEntity(String table_name, String table_explain) {
        this.table_name = table_name;
        this.table_explain = table_explain;
    }

    public String getTable_name() {
        return table_name;
    }

    public String getTable_explain() {
        return table_explain;
    }
}