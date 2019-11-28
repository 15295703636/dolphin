package org.cs.dp.radar.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.*;
import org.cs.dp.radar.api.feign.IMruClient;
import org.cs.dp.radar.service.IMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * mru接口调用
 */
@RestController
public class MruClient implements IMruClient {

    @Autowired
    IMruService iMruService;

    @Override
    @GetMapping(API_PREFIX+"/createUt12")
    public ReturnInfo createUt12(@RequestBody  Ut12Entity ut12Entity) {
        iMruService.createUt12(ut12Entity);
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/login")
    public ReturnInfo login(@RequestBody RestWebLoginReq restWebLoginReq,@RequestParam String url) {
        return iMruService.login(restWebLoginReq,url);
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/add")
    public ReturnInfo startConference(@RequestBody RestConfReq restConfReq,@RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/getConferences")
    public ReturnInfo getConferences(@RequestParam String token,@RequestParam String url,
                                     @RequestParam String startTime,@RequestParam String endTime) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/terminate")
    public ReturnInfo stopConference(@RequestParam String confId,@RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/parties/add")
    public ReturnInfo addPeer(@RequestBody RestPartyReq restPartyReq,@RequestParam String confId,
                              @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/parties/delete")
    public ReturnInfo removePeer(@RequestParam String confId,@RequestParam String partyId,
                                 @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/parties/connect")
    public ReturnInfo callPeer(@RequestParam String confId,@RequestParam String partyId,
                               @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/parties/disconnect")
    public ReturnInfo hangupPeer(@RequestParam String confId,@RequestParam String partyId,
                                 @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/info")
    public ReturnInfo getConfInfo(@RequestParam String confId, @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/switchToDiscussionMode")
    public ReturnInfo switch2discussionMode(@RequestParam String confId, @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/setAsLecturer")
    public ReturnInfo setLecturer(@RequestParam String confId,@RequestParam String partyId,
                                  @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/startLiveStreaming")
    public ReturnInfo startLiveStreaming(@RequestParam String confId,@RequestBody RestLiveStreamingReq restLiveStreamingReq,
                                         @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/stopLiveStreaming")
    public ReturnInfo stopLiveStreaming(@RequestParam String confId,
                                        @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/muteAudioMultipleParties")
    public ReturnInfo muteMultipleParties(@RequestParam String confId,@RequestParam boolean muteAudio,
                                          @RequestBody List<String> peers,
                                          @RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/getParties")
    public ReturnInfo getConferencePeers(@RequestParam String confId,@RequestParam String token,@RequestParam String url) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/setRecordingLivingPeopleLayout")
    public ReturnInfo setLivingStreamLayout(@RequestParam String confId,@RequestParam String token,@RequestParam String url,
                                            @RequestBody  RestPartyLayout restPartyLayout) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/conferences/setLayout")
    public ReturnInfo setPeerLayout(@RequestParam String confId,@RequestParam String peerId,
                                    @RequestParam String token,@RequestParam String url,
                                    @RequestBody RestPartyLayout restPartyLayoutt) {
        return null;
    }

    @Override
    @PostMapping(API_PREFIX+"/getEndpoints")
    public ReturnInfo getEndpoints(@RequestParam String token,@RequestParam String url) {
        return iMruService.getEndpoints(token,url);
    }

    @Override
    @PostMapping(API_PREFIX+"/addEndpoint")
    public ReturnInfo addEndpoint(@RequestParam String token,@RequestParam String url,
                                  @RequestBody RestEndpointReq restEndpointReq) {
        return iMruService.addEndpoint(token,url,restEndpointReq);
    }

    @Override
    @PostMapping(API_PREFIX+"/updateEndpoint")
    public ReturnInfo updateEndpoint(@RequestParam String token,@RequestParam String url,
                                     @RequestParam String endpointId,@RequestBody RestEndpointReq restEndpointReq) {
        return iMruService.updateEndpoint(token,url,endpointId,restEndpointReq);
    }

    @Override
    @PostMapping(API_PREFIX+"/deleteEndpoint")
    public ReturnInfo deleteEndpoint(@RequestParam String token,@RequestParam String url,
                                     @RequestParam String endpointId) {
        return iMruService.deleteEndpoint(token, url, endpointId);
    }

    @Override
    @PostMapping(API_PREFIX+"/getEndpoint")
    public ReturnInfo getEndpoint(@RequestParam String token,@RequestParam String url,
                                  @RequestParam String endpointId) {
        return iMruService.getEndpoint(token, url, endpointId);
    }
}
