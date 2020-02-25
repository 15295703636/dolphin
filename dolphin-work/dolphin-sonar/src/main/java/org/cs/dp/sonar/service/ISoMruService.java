package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ISoMruService {

    String POINT_GETS = "getEndpoints";
    String POINT_ADD = "addEndpoint";
    String POINT_UPDATE = "updateEndpoint";
    String POINT_DEL = "deleteEndpoint";
    String POINT_GET = "getEndpoint";

    //预约会议
    String CONFERENCE_START = "startConference";
    String CONFERENCE_STOP = "stopConference";
    //进行中的会议添加终端
    String CONFERENCE_ADDPEER = "addPeer";
    //进行中的会议删除终端
    String CONFERENCE_REMOVEPEER = "removePeer";
    //进行中的会议呼叫终端
    String CONFERENCE_CALLPEER = "callPeer";
    //进行中的会议挂断终端
    String CONFERENCE_HANGUPPEER="hangupPeer";

    ReturnInfo getServer(String method, Object obj);

    ReturnInfo createUt12(Ut12Entity ut12Entity);

    ReturnInfo login(RestWebLoginReq restWebLoginReq, String url);

    ReturnInfo startConference(RestConfReq restConfReq, String token, String url);

    ReturnInfo getConferences(String token, String url,
                              String startTime, String endTime);

    ReturnInfo stopConference(String confId, String token, String url);

    ReturnInfo addPeer(RestPartyReq restPartyReq, String confId,
                       String token, String url);

    ReturnInfo removePeer(String confId, String partyId,
                          String token, String url);

    ReturnInfo callPeer(String confId, String partyId,
                        String token, String url);

    ReturnInfo hangupPeer(String confId, String partyId,
                          String token, String url);

    ReturnInfo getConfInfo(String confId, String token, String url);

    ReturnInfo switch2discussionMode(String confId, String token, String url);

    ReturnInfo setLecturer(String confId, String partyId,
                           String token, String url);

    ReturnInfo startLiveStreaming(String confId, RestLiveStreamingReq restLiveStreamingReq,
                                  String token, String url);

    ReturnInfo stopLiveStreaming(String confId,
                                 String token, String url);

    ReturnInfo muteMultipleParties(String confId, boolean muteAudio,
                                   List<String> peers,
                                   String token, String url);

    ReturnInfo getConferencePeers(String confId, String token, String url);

    ReturnInfo setLivingStreamLayout(String confId, String token, String url,
                                     RestPartyLayout restPartyLayout);

    ReturnInfo setPeerLayout(String confId, String peerId,
                             String token, String url,
                             RestPartyLayout restPartyLayout);

    ReturnInfo getEndpoints(String token, String url);

    ReturnInfo addEndpoint(String token, String url, RestEndpointReq restEndpointReq);

    ReturnInfo updateEndpoint(String token, String url, String endpointId, RestEndpointReq restEndpointReq);

    ReturnInfo deleteEndpoint(String token, String url, String endpointId);

    ReturnInfo getEndpoint(String token, String url, String endpointId);

}
