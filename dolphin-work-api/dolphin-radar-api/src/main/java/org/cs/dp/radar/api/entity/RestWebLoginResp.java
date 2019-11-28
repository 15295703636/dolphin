package org.cs.dp.radar.api.entity;

public class RestWebLoginResp {
    private String token;
    private RestUser profile;
    private String zoneRole;
    private String reportUrlPrefix;
    private String fileDownloadUrlPrefix;
    private String portModeEnabled;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RestUser getProfile() {
        return profile;
    }

    public void setProfile(RestUser profile) {
        this.profile = profile;
    }

    public String getZoneRole() {
        return zoneRole;
    }

    public void setZoneRole(String zoneRole) {
        this.zoneRole = zoneRole;
    }

    public String getReportUrlPrefix() {
        return reportUrlPrefix;
    }

    public void setReportUrlPrefix(String reportUrlPrefix) {
        this.reportUrlPrefix = reportUrlPrefix;
    }

    public String getFileDownloadUrlPrefix() {
        return fileDownloadUrlPrefix;
    }

    public void setFileDownloadUrlPrefix(String fileDownloadUrlPrefix) {
        this.fileDownloadUrlPrefix = fileDownloadUrlPrefix;
    }

    public String getPortModeEnabled() {
        return portModeEnabled;
    }

    public void setPortModeEnabled(String portModeEnabled) {
        this.portModeEnabled = portModeEnabled;
    }

    @Override
    public String toString() {
        return "RestWebLoginResp{" +
                "token='" + token + '\'' +
                ", profile=" + profile +
                ", zoneRole='" + zoneRole + '\'' +
                ", reportUrlPrefix='" + reportUrlPrefix + '\'' +
                ", fileDownloadUrlPrefix='" + fileDownloadUrlPrefix + '\'' +
                ", portModeEnabled='" + portModeEnabled + '\'' +
                '}';
    }
}
