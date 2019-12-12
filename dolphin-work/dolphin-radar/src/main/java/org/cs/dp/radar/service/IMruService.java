package org.cs.dp.radar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IMruService {
    ReturnInfo createUt12(Ut12Entity ut12Entity);
    ReturnInfo login(RestWebLoginReq restWebLoginReq,String url);
    ReturnInfo startConference(RestConfReq restConfReq, String token, String url);
    ReturnInfo getConferences( String token, String url,
                               String startTime, String endTime);
    ReturnInfo stopConference( String confId, String token, String url);
    ReturnInfo addPeer( RestPartyReq restPartyReq,  String confId,
                        String token,  String url);
    ReturnInfo removePeer( String confId, String partyId,
                           String token, String url);
    ReturnInfo callPeer( String confId, String partyId,
                         String token, String url);
    ReturnInfo hangupPeer( String confId, String partyId,
                           String token, String url);
    ReturnInfo getConfInfo( String confId,  String token, String url);
    ReturnInfo switch2discussionMode( String confId,  String token, String url);
    ReturnInfo setLecturer( String confId, String partyId,
                            String token, String url);
    ReturnInfo startLiveStreaming( String confId, RestLiveStreamingReq restLiveStreamingReq,
                                   String token, String url);
    ReturnInfo stopLiveStreaming( String confId,
                                  String token, String url);
    ReturnInfo muteMultipleParties( String confId, boolean muteAudio,
                                    List<String> peers,
                                    String token, String url);
    ReturnInfo getConferencePeers( String confId, String token, String url);
    ReturnInfo setLivingStreamLayout( String confId, String token, String url,
                                       RestPartyLayout restPartyLayout);
    ReturnInfo setPeerLayout( String confId, String peerId,
                              String token, String url,
                              RestPartyLayout restPartyLayout);
    ReturnInfo getEndpoints(  String token, String url);
    ReturnInfo addEndpoint( String token, String url,
                            RestEndpointReq restEndpointReq);
    ReturnInfo updateEndpoint( String token, String url,
                               String endpointId, RestEndpointReq restEndpointReq);
    ReturnInfo deleteEndpoint( String token, String url,
                               String endpointId);
    ReturnInfo getEndpoint( String token, String url,
                            String endpointId);
    ReturnInfo addUser( String token, String url,
                        RestOrgUserReq restOrgUserReq);
    ReturnInfo updateUser( String token, String url,
                           String userId, RestOrgUserReq restOrgUserReq);
    ReturnInfo getUsers(  String token, String url);
    ReturnInfo getUser(  String token, String userId, String url);
    ReturnInfo deleteUser( String token, String url,
                           String userId);

}
