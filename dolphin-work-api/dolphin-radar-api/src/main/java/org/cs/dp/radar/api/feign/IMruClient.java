package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.AppConstant;
import org.cs.dp.radar.api.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = AppConstant.APPLICATION_RADAR_NAME,
        fallback = IMruClientFallBack.class)
public interface IMruClient {
    String API_PREFIX = "/mru";


    @GetMapping(API_PREFIX+"/createUt12")
    ReturnInfo createUt12(@RequestBody Ut12Entity ut12Entity);

    /**
     * 获取token
     * @param restWebLoginReq
     * @param url
     * @return
     */
    @PostMapping(API_PREFIX+"/login")
    ReturnInfo login(@RequestBody RestWebLoginReq restWebLoginReq, @RequestParam String url);

    /**
     * 预约会议
     * @param restConfReq
     * @param token
     * @param url /api/rest/v2.0/conferences
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/add")
    ReturnInfo startConference(@RequestBody RestConfReq restConfReq,@RequestParam String token,@RequestParam String url);

    /**
     * 获取会议列表
     * @param token
     * @param url /api/rest/v2.0/conferences
     * @param startTime 以毫秒为单位的Unix时间戳
     * @param endTime
     * @return
     */
    @PostMapping(API_PREFIX+"/getConferences")
    ReturnInfo getConferences(@RequestParam String token,@RequestParam String url,
                              @RequestParam String startTime,@RequestParam String endTime);

    /**
     * 结束会议
     * @param confId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/terminate")
    ReturnInfo stopConference(@RequestParam String confId,@RequestParam String token,@RequestParam String url);

    /**
     * 添加终端
     * @param restPartyReq
     * @param confId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/parties
     * @return
     */
    @PostMapping(API_PREFIX+"/parties/add")
    ReturnInfo addPeer(@RequestBody RestPartyReq restPartyReq,@RequestParam String confId,
                       @RequestParam String token,@RequestParam String url);

    /**
     * 删除终端
     * @param confId
     * @param partyId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/parties/{partyId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX+"/parties/delete")
    ReturnInfo removePeer(@RequestParam String confId,@RequestParam String partyId,
                          @RequestParam String token,@RequestParam String url);

    /**
     * 呼叫终端
     * @param confId
     * @param partyId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/parties/{partyId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX+"/parties/connect")
    ReturnInfo callPeer(@RequestParam String confId,@RequestParam String partyId,
                          @RequestParam String token,@RequestParam String url);

    /**
     * 挂断终端
     * @param confId
     * @param partyId
     * @param token
     * @param url
     * @return
     */
    @PostMapping(API_PREFIX+"/parties/disconnect")
    ReturnInfo hangupPeer(@RequestParam String confId,@RequestParam String partyId,
                        @RequestParam String token,@RequestParam String url);

    /**
     * 获取会议信息
     * @param confId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/info")
    ReturnInfo getConfInfo(@RequestParam String confId, @RequestParam String token,@RequestParam String url);

    /**
     * 切换至讨论模式
     * @param confId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/%s/control/switchToDiscussionMode
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/switchToDiscussionMode")
    ReturnInfo switch2discussionMode(@RequestParam String confId, @RequestParam String token,@RequestParam String url);

    /**
     * 设置发言者
     * @param confId
     * @param partyId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/parties/{partyId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/setAsLecturer")
    ReturnInfo setLecturer(@RequestParam String confId,@RequestParam String partyId,
                           @RequestParam String token,@RequestParam String url);

    /**
     * 开启直播
     * @param confId
     * @param restLiveStreamingReq
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/startLiveStreaming")
    ReturnInfo startLiveStreaming(@RequestParam String confId,@RequestBody RestLiveStreamingReq restLiveStreamingReq,
                                    @RequestParam String token,@RequestParam String url);
    /**
     * 结束直播
     * @param confId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/stopLiveStreaming")
    ReturnInfo stopLiveStreaming(@RequestParam String confId,
                                    @RequestParam String token,@RequestParam String url);

    /**
     * 静音、取消静音部分终端
     * @param confId
     * @param muteAudio
     * @param peers
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/muteAudioMultipleParties")
    ReturnInfo muteMultipleParties(@RequestParam String confId,@RequestParam boolean muteAudio,
                                   @RequestBody List<String> peers,
                                   @RequestParam String token,@RequestParam String url);

    /**
     * 获取与会终端信息
     * @param confId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/parties
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/getParties")
    ReturnInfo getConferencePeers(@RequestParam String confId,@RequestParam String token,@RequestParam String url);

    /**
     * 设置直播分屏
     * @param confId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @param restPartyLayout
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/setRecordingLivingPeopleLayout")
    ReturnInfo setLivingStreamLayout(@RequestParam String confId,@RequestParam String token,@RequestParam String url,
                                     @RequestBody  RestPartyLayout restPartyLayout);

    /**
     * 设置与会终端分屏
     * @param confId
     * @param peerId
     * @param token
     * @param url /api/rest/v2.0/ongoingConferences/{confId}/parties/{partyId}/control/{controlType}
     * @param restPartyLayout
     * @return
     */
    @PostMapping(API_PREFIX+"/conferences/setLayout")
    ReturnInfo setPeerLayout(@RequestParam String confId,@RequestParam String peerId,
                             @RequestParam String token,@RequestParam String url,
                             @RequestBody RestPartyLayout restPartyLayout);

    /**
     * 获取终端列表
     * @param token
     * @param url /api/rest/v2.0/endpoints
     * @return
     */
    @PostMapping(API_PREFIX+"/getEndpoints")
    ReturnInfo getEndpoints( @RequestParam String token,@RequestParam String url);

    /**
     * 添加终端
     * @param token
     * @param url /api/rest/v2.0/endpoints
     * @param restEndpointReq
     * @return
     */
    @PostMapping(API_PREFIX+"/addEndpoint")
    ReturnInfo addEndpoint(@RequestParam String token,@RequestParam String url,
                           @RequestBody RestEndpointReq restEndpointReq);

    /**
     * 修改终端
     * @param token
     * @param url /api/rest/v2.0/endpoints/{endpointId}
     * @param endpointId
     * @param restEndpointReq
     * @return
     */
    @PostMapping(API_PREFIX+"/updateEndpoint")
    ReturnInfo updateEndpoint(@RequestParam String token,@RequestParam String url,
                              @RequestParam String endpointId,@RequestBody RestEndpointReq restEndpointReq);

    /**
     * 删除终端
     * @param token
     * @param url /api/rest/v2.0/endpoints/{endpointId}
     * @param endpointId
     * @return
     */
    @PostMapping(API_PREFIX+"/deleteEndpoint")
    ReturnInfo deleteEndpoint(@RequestParam String token,@RequestParam String url,
                              @RequestParam String endpointId);

    /**
     * 获取终端详情
     * @param token
     * @param url /api/rest/v2.0/endpoints/{endpointId}
     * @param endpointId
     * @return
     */
    @PostMapping(API_PREFIX+"/getEndpoint")
    ReturnInfo getEndpoint(@RequestParam String token,@RequestParam String url,
                           @RequestParam String endpointId);

    /**
     * 添加用户
     * @param token
     * @param url /api/rest/v2.0/org/users
     * @param restOrgUserReq
     * @return
     */
    @PostMapping(API_PREFIX+"/addUser")
    ReturnInfo addUser(@RequestParam String token,@RequestParam String url,
                           @RequestBody RestOrgUserReq restOrgUserReq);

    /**
     * 更新用户
     * @param token
     * @param url /api/rest/v2.0/org/users/{userId}
     * @param userId
     * @param restOrgUserReq
     * @return
     */
    @PostMapping(API_PREFIX+"/updateUser")
    ReturnInfo updateUser(@RequestParam String token,@RequestParam String url,
                              @RequestParam String userId,@RequestBody RestOrgUserReq restOrgUserReq);

    /**
     * 获取用户列表
     * @param token
     * @param url /api/rest/v2.0/users
     * @return
     */
    @PostMapping(API_PREFIX+"/getUsers")
    ReturnInfo getUsers( @RequestParam String token,@RequestParam String url);

    /**
     * 用户详情
     * @param token
     * @param userId
     * @param url /api/rest/v2.0/users/{userId}
     * @return
     */
    @PostMapping(API_PREFIX+"/getUser")
    ReturnInfo getUser( @RequestParam String token,@RequestParam String userId,@RequestParam String url);

    /**
     * 删除用户
     * @param token
     * @param url /api/rest/v2.0/org/users/{userId}
     * @param userId
     * @return
     */
    @PostMapping(API_PREFIX+"/deleteUser")
    ReturnInfo deleteUser(@RequestParam String token,@RequestParam String url,
                              @RequestParam String userId);
}
