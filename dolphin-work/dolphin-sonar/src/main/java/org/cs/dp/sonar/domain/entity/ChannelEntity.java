package org.cs.dp.sonar.domain.entity;

public class ChannelEntity {
    private Integer channel_id;

    private String channel_name;

    private Integer channel_grade;

    private Integer pre_channel_id;

    private Integer org_id;

    private String alias_name;

    public ChannelEntity(Integer channel_id, String channel_name, Integer channel_grade, Integer pre_channel_id, Integer org_id, String alias_name) {
        this.channel_id = channel_id;
        this.channel_name = channel_name;
        this.channel_grade = channel_grade;
        this.pre_channel_id = pre_channel_id;
        this.org_id = org_id;
        this.alias_name = alias_name;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public Integer getChannel_grade() {
        return channel_grade;
    }

    public Integer getPre_channel_id() {
        return pre_channel_id;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public String getAlias_name() {
        return alias_name;
    }
}