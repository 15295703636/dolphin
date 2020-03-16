package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:36
 */
public class RestPartyReq {
    List<RestEndpoint> endpoints;
    RestAvcPartyReq	avcEndpoint	;

    public List<RestEndpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<RestEndpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public RestAvcPartyReq getAvcEndpoint() {
        return avcEndpoint;
    }

    public void setAvcEndpoint(RestAvcPartyReq avcEndpoint) {
        this.avcEndpoint = avcEndpoint;
    }
}
