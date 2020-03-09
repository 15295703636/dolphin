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


    @GetMapping(API_PREFIX + "/createUt12")
    ReturnInfo createUt12(@RequestBody Ut12Entity ut12Entity);

    /**
     * 获取token
     *
     * @param restWebLoginReq
     * @param url
     * @return
     */
    @PostMapping(API_PREFIX + "/login")
    ReturnInfo login(@RequestBody RestWebLoginReq restWebLoginReq, @RequestParam(name = "url") String url);

    /**
     * 预约会议
     *
     * @param restConfReq
     * @param token
     * @param url         /api/rest/v2.0/conferences
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/add")
    ReturnInfo startConference(@RequestBody RestConfReq restConfReq,
                               @RequestParam(name = "token") String token,
                               @RequestParam(name = "url") String url);

    /**
     * 获取会议列表
     *
     * @param token
     * @param url       /api/rest/v2.0/conferences
     * @param startTime 以毫秒为单位的Unix时间戳
     * @param endTime
     * @return
     */
    @PostMapping(API_PREFIX + "/getConferences")
    ReturnInfo getConferences(@RequestParam(name = "token") String token,
                              @RequestParam(name = "url") String url,
                              @RequestParam(name = "startTime") String startTime,
                              @RequestParam(name = "endTime") String endTime);

    /**
     * 结束会议
     *
     * @param confId
     * @param token
     * @param url    /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/terminate")
    ReturnInfo stopConference(@RequestParam(name = "confId") String confId,
                              @RequestParam(name = "token") String token,
                              @RequestParam(name = "url") String url);

    /**
     * 添加终端
     *
     * @param restPartyReq
     * @param confId
     * @param token
     * @param url          /api/rest/v2.0/ongoingConferences/{confId}/parties
     * @return
     */
    @PostMapping(API_PREFIX + "/parties/add")
    ReturnInfo addPeer(@RequestBody RestPartyReq restPartyReq,
                       @RequestParam(name = "confId") String confId,
                       @RequestParam(name = "token") String token,
                       @RequestParam(name = "url") String url);

    /**
     * 删除终端
     *
     * @param confId
     * @param partyId
     * @param token
     * @param url     /api/rest/v2.0/ongoingConferences/{confId}/parties/{partyId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX + "/parties/delete")
    ReturnInfo removePeer(@RequestParam(name = "confId") String confId,
                          @RequestParam(name = "partyId") String partyId,
                          @RequestParam(name = "token") String token,
                          @RequestParam(name = "url") String url);

    /**
     * 呼叫终端
     *
     * @param confId
     * @param partyId
     * @param token
     * @param url     /api/rest/v2.0/ongoingConferences/{confId}/parties/{partyId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX + "/parties/connect")
    ReturnInfo callPeer(@RequestParam(name = "confId") String confId,
                        @RequestParam(name = "partyId") String partyId,
                        @RequestParam(name = "token") String token,
                        @RequestParam(name = "url") String url);

    /**
     * 挂断终端
     *
     * @param confId
     * @param partyId
     * @param token
     * @param url
     * @return
     */
    @PostMapping(API_PREFIX + "/parties/disconnect")
    ReturnInfo hangupPeer(@RequestParam(name = "confId") String confId,
                          @RequestParam(name = "partyId") String partyId,
                          @RequestParam(name = "token") String token,
                          @RequestParam(name = "url") String url);

    /**
     * 获取会议信息
     *
     * @param confId
     * @param token
     * @param url    /api/rest/v2.0/ongoingConferences/{confId}
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/info")
    ReturnInfo getConfInfo(@RequestParam(name = "confId") String confId,
                           @RequestParam(name = "token") String token,
                           @RequestParam(name = "url") String url);

    /**
     * 切换至讨论模式
     *
     * @param confId
     * @param token
     * @param url    /api/rest/v2.0/ongoingConferences/%s/control/switchToDiscussionMode
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/switchToDiscussionMode")
    ReturnInfo switch2discussionMode(@RequestParam(name = "confId") String confId,
                                     @RequestParam(name = "token") String token,
                                     @RequestParam(name = "url") String url);

    /**
     * 设置发言者
     *
     * @param confId
     * @param partyId
     * @param token
     * @param url     /api/rest/v2.0/ongoingConferences/{confId}/parties/{partyId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/setAsLecturer")
    ReturnInfo setLecturer(@RequestParam(name = "confId") String confId,
                           @RequestParam(name = "partyId") String partyId,
                           @RequestParam(name = "token") String token,
                           @RequestParam(name = "url") String url);

    /**
     * 开启直播
     *
     * @param confId
     * @param restLiveStreamingReq
     * @param token
     * @param url                  /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/startLiveStreaming")
    ReturnInfo startLiveStreaming(@RequestBody RestLiveStreamingReq restLiveStreamingReq,
                                  @RequestParam(name = "confId") String confId,
                                  @RequestParam(name = "token") String token,
                                  @RequestParam(name = "url") String url);

    /**
     * 结束直播
     *
     * @param confId
     * @param token
     * @param url    /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/stopLiveStreaming")
    ReturnInfo stopLiveStreaming(@RequestParam(name = "confId") String confId,
                                 @RequestParam(name = "token") String token,
                                 @RequestParam(name = "url") String url);

    /**
     * 静音、取消静音全部终端
     *
     * @param confId
     * @param muteAudio
     * @param token
     * @param url       /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/muteAudioAll")
    ReturnInfo muteAudioAll(
            @RequestParam(name = "confId") String confId,
            @RequestParam(name = "muteAudio") boolean muteAudio,
            @RequestParam(name = "token") String token,
            @RequestParam(name = "url") String url);

    /**
     * 静音、取消静音单个终端
     *
     * @param confId
     * @param muteAudio
     * @param token
     * @param url       /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/muteAudio")
    ReturnInfo muteAudio(
            @RequestParam(name = "peer") String peer,
            @RequestParam(name = "confId") String confId,
            @RequestParam(name = "muteAudio") boolean muteAudio,
            @RequestParam(name = "token") String token,
            @RequestParam(name = "url") String url);

    /**
     * 获取与会终端信息
     *
     * @param confId
     * @param token
     * @param url    /api/rest/v2.0/ongoingConferences/{confId}/parties
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/getParties")
    ReturnInfo getConferencePeers(@RequestParam(name = "confId") String confId,
                                  @RequestParam(name = "token") String token,
                                  @RequestParam(name = "url") String url);

    /**
     * 设置直播分屏
     *
     * @param confId
     * @param token
     * @param url             /api/rest/v2.0/ongoingConferences/{confId}/control/{controlType}
     * @param restPartyLayout
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/setRecordingLivingPeopleLayout")
    ReturnInfo setLivingStreamLayout(@RequestParam(name = "confId") String confId,
                                     @RequestParam(name = "token") String token,
                                     @RequestParam(name = "url") String url,
                                     @RequestBody RestPartyLayout restPartyLayout);

    /**
     * 设置与会终端分屏
     *
     * @param confId
     * @param peerId
     * @param token
     * @param url             /api/rest/v2.0/ongoingConferences/{confId}/parties/{partyId}/control/{controlType}
     * @param restPartyLayout
     * @return
     */
    @PostMapping(API_PREFIX + "/conferences/setLayout")
    ReturnInfo setPeerLayout(@RequestParam(name = "confId") String confId,
                             @RequestParam(name = "peerId") String peerId,
                             @RequestParam(name = "token") String token,
                             @RequestParam(name = "url") String url,
                             @RequestBody RestPartyLayout restPartyLayout);

    /**
     * 获取终端列表
     *
     * @param token
     * @param url   /api/rest/v2.0/endpoints
     * @return
     */
    @PostMapping(API_PREFIX + "/getEndpoints")
    ReturnInfo getEndpoints(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url);

    /**
     * 添加终端
     *
     * @param token
     * @param url             /api/rest/v2.0/endpoints
     * @param restEndpointReq
     * @return
     */
    @PostMapping(API_PREFIX + "/addEndpoint")
    ReturnInfo addEndpoint(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                           @RequestBody RestEndpointReq restEndpointReq);

    /**
     * 修改终端
     *
     * @param token
     * @param url             /api/rest/v2.0/endpoints/{endpointId}
     * @param endpointId
     * @param restEndpointReq
     * @return
     */
    @PostMapping(API_PREFIX + "/updateEndpoint")
    ReturnInfo updateEndpoint(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                              @RequestParam(name = "endpointId") String endpointId, @RequestBody RestEndpointReq restEndpointReq);

    /**
     * 删除终端
     *
     * @param token
     * @param url        /api/rest/v2.0/endpoints/{endpointId}
     * @param endpointId
     * @return
     */
    @PostMapping(API_PREFIX + "/deleteEndpoint")
    ReturnInfo deleteEndpoint(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                              @RequestParam(name = "endpointId") String endpointId);

    /**
     * 获取终端详情
     *
     * @param token
     * @param url        /api/rest/v2.0/endpoints/{endpointId}
     * @param endpointId
     * @return
     */
    @PostMapping(API_PREFIX + "/getEndpoint")
    ReturnInfo getEndpoint(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                           @RequestParam(name = "endpointId") String endpointId);

    /**
     * 添加用户
     *
     * @param token
     * @param url            /api/rest/v2.0/org/users
     * @param restOrgUserReq
     * @return
     */
    @PostMapping(API_PREFIX + "/addUser")
    ReturnInfo addUser(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                       @RequestBody RestOrgUserReq restOrgUserReq);

    /**
     * 更新用户
     *
     * @param token
     * @param url            /api/rest/v2.0/org/users/{userId}
     * @param userId
     * @param restOrgUserReq
     * @return
     */
    @PostMapping(API_PREFIX + "/updateUser")
    ReturnInfo updateUser(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                          @RequestParam(name = "userId") String userId, @RequestBody RestOrgUserReq restOrgUserReq);

    /**
     * 获取用户列表
     *
     * @param token
     * @param url   /api/rest/v2.0/users
     * @return
     */
    @PostMapping(API_PREFIX + "/getUsers")
    ReturnInfo getUsers(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url);

    /**
     * 用户详情
     *
     * @param token
     * @param userId
     * @param url    /api/rest/v2.0/users/{userId}
     * @return
     */
    @PostMapping(API_PREFIX + "/getUser")
    ReturnInfo getUser(@RequestParam(name = "token") String token,
                       @RequestParam(name = "userId") String userId,
                       @RequestParam(name = "url") String url);

    /**
     * 删除用户
     *
     * @param token
     * @param url    /api/rest/v2.0/org/users/{userId}
     * @param userId
     * @return
     */
    @PostMapping(API_PREFIX + "/deleteUser")
    ReturnInfo deleteUser(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                          @RequestParam(name = "userId") String userId);

    @PostMapping(API_PREFIX + "/addDept")
    ReturnInfo addDept(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                       @RequestBody RestDeptReq restDeptReq);

    @PostMapping(API_PREFIX + "/updateDept")
    ReturnInfo updateDept(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                          @RequestParam(name = "deptId") String deptId, @RequestBody RestDeptReq restDeptReq);

    @PostMapping(API_PREFIX + "/getDepts")
    ReturnInfo getDepts(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url);

    @PostMapping(API_PREFIX + "/getDept")
    ReturnInfo getDept(@RequestParam(name = "token") String token, @RequestParam(name = "deptId") String deptId, @RequestParam(name = "url") String url);

    @PostMapping(API_PREFIX + "/deleteDept")
    ReturnInfo deleteDept(@RequestParam(name = "token") String token, @RequestParam(name = "url") String url,
                          @RequestParam(name = "deptId") String deptId);
}
