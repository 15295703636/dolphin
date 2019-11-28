package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:32
 */
public class RestServiceInfo {
    private String version	;//	平台版本号	-
    private RestFeatureSupport featureSupport	;//	平台的功能支持信息

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public RestFeatureSupport getFeatureSupport() {
        return featureSupport;
    }

    public void setFeatureSupport(RestFeatureSupport featureSupport) {
        this.featureSupport = featureSupport;
    }
}
