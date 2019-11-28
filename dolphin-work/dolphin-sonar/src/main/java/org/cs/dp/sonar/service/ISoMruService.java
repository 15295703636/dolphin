package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.RestEndpointReq;
import org.cs.dp.radar.api.entity.RestWebLoginReq;
import org.cs.dp.radar.api.entity.Ut12Entity;

import java.security.NoSuchAlgorithmException;

public interface ISoMruService {
    ReturnInfo createUt12(Ut12Entity ut12Entity);
    ReturnInfo login (RestWebLoginReq restWebLoginReq,String url);
    ReturnInfo getEndpoints(String token, String url);
    ReturnInfo addEndpoint(String token, String url, RestEndpointReq restEndpointReq);
    ReturnInfo updateEndpoint( String token, String url,String endpointId, RestEndpointReq restEndpointReq);
    ReturnInfo deleteEndpoint( String token, String url,String endpointId);
    ReturnInfo getEndpoint( String token, String url,String endpointId);
}
