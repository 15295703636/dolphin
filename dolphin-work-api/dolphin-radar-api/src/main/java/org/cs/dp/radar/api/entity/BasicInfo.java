package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:43
 */
public class BasicInfo {
    private String guid	;//	会议ID	-
    private String name	;//	会议名称	-
    private String conferenceParameterGuid	;//	-	-
    private int duration	;//	会议时长	单位为秒
    private String id	;//	会议号	-
    private String conferencePassword	;//	会议密码	-
    private boolean dialOutManually	;//	-	-

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConferenceParameterGuid() {
        return conferenceParameterGuid;
    }

    public void setConferenceParameterGuid(String conferenceParameterGuid) {
        this.conferenceParameterGuid = conferenceParameterGuid;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConferencePassword() {
        return conferencePassword;
    }

    public void setConferencePassword(String conferencePassword) {
        this.conferencePassword = conferencePassword;
    }

    public boolean isDialOutManually() {
        return dialOutManually;
    }

    public void setDialOutManually(boolean dialOutManually) {
        this.dialOutManually = dialOutManually;
    }
}
