package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.radar.api.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IMruClientFallBack implements IMruClient {

    @Override
    public ReturnInfo createUt12(Ut12Entity ut12Entity) {
        return ReturnInfo.build(MessageCode.COMMON_FAILURE_FLAG,"获取失败");
    }

    @Override
    public ReturnInfo login(RestWebLoginReq restWebLoginReq, String url) {
        return null;
    }

    @Override
    public ReturnInfo startConference(RestConfReq restConfReq, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo getConferences(String token, String url, String startTime, String endTime) {
        return null;
    }

    @Override
    public ReturnInfo stopConference(String confId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo addPeer(RestPartyReq restPartyReq, String confId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo removePeer(String confId, String partyId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo callPeer(String confId, String partyId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo hangupPeer(String confId, String partyId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo getConfInfo(String confId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo switch2discussionMode(String confId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo setLecturer(String confId, String partyId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo startLiveStreaming(RestLiveStreamingReq restLiveStreamingReq,String confId,  String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo stopLiveStreaming(String confId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo muteAudioAll(String confId, boolean muteAudio,  String token, String url) {
        return null;
    }
    @Override
    public ReturnInfo muteAudio(String peer,String confId, boolean muteAudio,  String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo getConferencePeers(String confId, String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo setLivingStreamLayout(String confId, String token, String url, RestPartyLayout restPartyLayout) {
        return null;
    }

    @Override
    public ReturnInfo setPeerLayout(String confId, String peerId, String token, String url, RestPartyLayout restPartyLayout) {
        return null;
    }

    @Override
    public ReturnInfo getEndpoints(String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo addEndpoint(String token, String url, RestEndpointReq restEndpointReq) {
        return null;
    }

    @Override
    public ReturnInfo updateEndpoint(String token, String url, String endpointId, RestEndpointReq restEndpointReq) {
        return null;
    }

    @Override
    public ReturnInfo deleteEndpoint(String token, String url, String endpointId) {
        return null;
    }

    @Override
    public ReturnInfo getEndpoint(String token, String url, String endpointId) {
        return null;
    }

    @Override
    public ReturnInfo addUser(String token, String url, RestOrgUserReq restOrgUserReq) {
        return null;
    }

    @Override
    public ReturnInfo updateUser(String token, String url, String userId, RestOrgUserReq restOrgUserReq) {
        return null;
    }

    @Override
    public ReturnInfo getUsers(String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo getUser(String token, String userId, String url) {
        return null;
    }

    @Override
    public ReturnInfo deleteUser(String token, String url, String userId) {
        return null;
    }

    @Override
    public ReturnInfo addDept(String token, String url, RestDeptReq restDeptReq) {
        return null;
    }

    @Override
    public ReturnInfo updateDept(String token, String url, String userId, RestDeptReq restDeptReq) {
        return null;
    }

    @Override
    public ReturnInfo getDepts(String token, String url) {
        return null;
    }

    @Override
    public ReturnInfo getDept(String token, String deptId, String url) {
        return null;
    }

    @Override
    public ReturnInfo deleteDept(String token, String url, String deptId) {
        return null;
    }
}
