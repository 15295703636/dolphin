package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.UserInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.SHA1Util;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.radar.api.entity.*;
import org.cs.dp.radar.api.feign.IMruClient;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.ServerEntity;
import org.cs.dp.sonar.mapper.ServerMapper;
import org.cs.dp.sonar.mapper.UcenterMapper;
import org.cs.dp.sonar.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 调用radar服务mru相关接口
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class ISoMruServiceImpl implements ISoMruService {
    @Autowired
    private IMruClient iMruClient;
    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private UcenterMapper ucenterMapper;

    @Override
    public ReturnInfo getServer(String method, Object obj) {
        log.info("云视讯：入参：{}——{}", method, JSONObject.toJSONString(obj));
        ReturnInfo returnInfo = null;
        String url = null;

        //获取云视讯服务地址
        List<ServerEntity> serverInfo = serverMapper.selectAll(15);
        if (serverInfo.size() > 0) {
            url = (String) serverInfo.get(0).getServer_ip();
        } else {
            return new ReturnInfo(MessageCode.Fail_Conn_EduContrl_Platform, "未维护云视讯服务地址");
        }

        UserInfo userInfo = ThreadLocalUserInfoUtil.get();

        //登录云视讯
        RestWebLoginReq restWebLoginReq = new RestWebLoginReq();
        restWebLoginReq.setAccount(userInfo.getUser_name());
        restWebLoginReq.setPassword(ucenterMapper.selectById(userInfo.getUser_id()));
        restWebLoginReq.setIntranet("true");
        returnInfo = login(restWebLoginReq, url);

        RestWebLoginResp restWebLoginResp = (RestWebLoginResp) returnInfo.getReturnData();
        String token = restWebLoginResp.getToken();

        switch (method) {
            case POINT_ADD:
                returnInfo = addEndpoint(token, url, (RestEndpointReq) obj);
                break;
            case POINT_DEL:
                returnInfo = deleteEndpoint(token, url, String.valueOf(obj));
                break;
            case POINT_UPDATE:
                EndpointReqBean endpointReq = (EndpointReqBean) obj;
                returnInfo = updateEndpoint(token, url, String.valueOf(endpointReq.getYsx_id()), (RestEndpointReq) obj);
                break;
            case POINT_GETS:
                returnInfo = getEndpoints(token, url);
                break;
            case CONFERENCE_START:
                returnInfo = startConference((RestConfReq) obj, token, url);
                break;
            case CONFERENCE_STOP:
                returnInfo = stopConference((String) obj, token, url);
                break;
            case CONFERENCE_ADDPEER:
                RestPartyReqBean restPartyReq = (RestPartyReqBean) obj;
                returnInfo = addPeer(restPartyReq, String.valueOf(restPartyReq.getYxs_id()), token, url);
                break;
            case CONFERENCE_REMOVEPEER:
                CourseDeviceReqBean courseDeviceReq = (CourseDeviceReqBean) obj;
                returnInfo = removePeer(String.valueOf(courseDeviceReq.getCourse_ysx_id()),
                        String.valueOf(courseDeviceReq.getYsx_device_id()), token, url);
                break;
            case CONFERENCE_CALLPEER:
                CourseDeviceReqBean courseDeviceReq2 = (CourseDeviceReqBean) obj;
                returnInfo = callPeer(String.valueOf(courseDeviceReq2.getCourse_ysx_id()),
                        String.valueOf(courseDeviceReq2.getYsx_device_id()), token, url);
                break;
            case CONFERENCE_HANGUPPEER:
                CourseDeviceReqBean courseDeviceReq3 = (CourseDeviceReqBean) obj;
                returnInfo = hangupPeer(String.valueOf(courseDeviceReq3.getCourse_ysx_id()),
                        String.valueOf(courseDeviceReq3.getYsx_device_id()), token, url);
                break;
            case CONFERENCE_MUTEAUDIO:
                CourseDeviceMuteReqBean courseDeviceMuteReq = (CourseDeviceMuteReqBean) obj;
                returnInfo = muteAudio(courseDeviceMuteReq.getYsx_device_id(), courseDeviceMuteReq.getYsx_course_id(), courseDeviceMuteReq.isMuteAudio(), token, url);
                break;
            case CONFERENCE_MUTEAUDIOALL:
                CourseDeviceMuteReqBean courseDeviceMuteReq2 = (CourseDeviceMuteReqBean) obj;
                returnInfo = muteAudioAll(courseDeviceMuteReq2.getYsx_course_id(), courseDeviceMuteReq2.isMuteAudio(), token, url);
                break;
            case CONFERENCE_GETCONFINFO:
                returnInfo = getConfInfo((String) obj, token, url);
                break;
            case CONFERENCE_SETLECTURER:
                CourseDeviceSetLecturerReqBean param = (CourseDeviceSetLecturerReqBean) obj;
                returnInfo = setLecturer(param.getYsx_course_id(), param.getYsx_device_id(), token, url);
                break;
            case CONFERENCE_SETDISCMODE:
                returnInfo = switch2discussionMode((String) obj, token, url);
                break;
            case CONFERENCE_SETPEERLAYOUT:
                RestPartyLayoutReqBean reqBean = (RestPartyLayoutReqBean) obj;
                returnInfo = setPeerLayout(reqBean.getYsx_course_id(), reqBean.getYsx_device_id(),
                        token, url, reqBean);
                break;
            default:
                returnInfo = new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "未查询到服务方法!");
                break;
        }
        log.info("云视讯：返回：{}——{}", method, JSONObject.toJSONString(returnInfo));
        return returnInfo;
    }

    @Override
    public ReturnInfo createUt12(Ut12Entity ut12Entity) {
        return iMruClient.createUt12(ut12Entity);
    }

    @Override
    public ReturnInfo login(RestWebLoginReq restWebLoginReq, String url) {
        try {
            restWebLoginReq.setPassword(SHA1Util.sha1(restWebLoginReq.getPassword()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        ReturnInfo returnInfo = iMruClient.login(restWebLoginReq, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestWebLoginResp restWebLoginResp = JSON.parseObject(s, new TypeReference<RestWebLoginResp>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 预约会议
     *
     * @param restConfReq
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo startConference(RestConfReq restConfReq, String token, String url) {
        ReturnInfo returnInfo = iMruClient.startConference(restConfReq, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestConf restConf = JSON.parseObject(s, RestConf.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 获取会议列表 TODO 未调用
     *
     * @param token
     * @param url
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ReturnInfo getConferences(String token, String url, String startTime, String endTime) {
        ReturnInfo returnInfo = iMruClient.getConferences(token, url, startTime, endTime);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            List<RestConfSummary> restConf = JSON.parseArray(s, RestConfSummary.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 结束会议
     *
     * @param confId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo stopConference(String confId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.stopConference(confId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 会议——添加终端
     *
     * @param restPartyReq
     * @param confId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo addPeer(RestPartyReq restPartyReq, String confId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.addPeer(restPartyReq, confId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestParty restConf = JSON.parseObject(s, RestParty.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 会议——删除终端
     *
     * @param confId
     * @param partyId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo removePeer(String confId, String partyId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.removePeer(confId, partyId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 会议——呼叫终端
     *
     * @param confId
     * @param partyId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo callPeer(String confId, String partyId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.callPeer(confId, partyId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 会议——挂断终端
     *
     * @param confId
     * @param partyId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo hangupPeer(String confId, String partyId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.hangupPeer(confId, partyId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 获取会议信息
     *
     * @param confId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo getConfInfo(String confId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.getConfInfo(confId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestOngoingConf restConf = JSON.parseObject(s, RestOngoingConf.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 切换至讨论模式
     *
     * @param confId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo switch2discussionMode(String confId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.switch2discussionMode(confId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 设置发言者(设置主讲)
     *
     * @param confId
     * @param partyId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo setLecturer(String confId, String partyId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.setLecturer(confId, partyId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 开启直播 TODO 未调用
     *
     * @param confId
     * @param restLiveStreamingReq
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo startLiveStreaming(String confId, RestLiveStreamingReq restLiveStreamingReq, String token, String url) {
        ReturnInfo returnInfo = iMruClient.startLiveStreaming(restLiveStreamingReq, confId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 结束直播 TODO 未调用
     *
     * @param confId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo stopLiveStreaming(String confId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.stopLiveStreaming(confId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 静音、取消静音部分终端
     *
     * @param confId
     * @param muteAudio
     * @param peers
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo muteAudioAll(String confId, boolean muteAudio, String token, String url) {
        ReturnInfo returnInfo = iMruClient.muteAudioAll(confId, muteAudio, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 静音、取消静音部分终端
     *
     * @param confId
     * @param muteAudio
     * @param peers
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo muteAudio(String peer, String confId, boolean muteAudio, String token, String url) {
        ReturnInfo returnInfo = iMruClient.muteAudio(peer, confId, muteAudio, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 获取与会终端信息 TODO 未调用
     *
     * @param confId
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo getConferencePeers(String confId, String token, String url) {
        ReturnInfo returnInfo = iMruClient.getConferencePeers(confId, token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            List<RestParty> restConf = JSON.parseArray(s, RestParty.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 设置直播分屏 TODO 未调用
     *
     * @param confId
     * @param token
     * @param url
     * @param restPartyLayout
     * @return
     */
    @Override
    public ReturnInfo setLivingStreamLayout(String confId, String token, String url, RestPartyLayout restPartyLayout) {
        ReturnInfo returnInfo = iMruClient.setLivingStreamLayout(confId, token, url, restPartyLayout);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 设置与会终端分屏
     *
     * @param confId
     * @param peerId
     * @param token
     * @param url
     * @param restPartyLayout
     * @return
     */
    @Override
    public ReturnInfo setPeerLayout(String confId, String peerId, String token, String url, RestPartyLayout restPartyLayout) {
        ReturnInfo returnInfo = iMruClient.setPeerLayout(confId, peerId, token, url, restPartyLayout);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map restConf = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(restConf);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 查询终端列表
     *
     * @param token
     * @param url
     * @return
     */
    @Override
    public ReturnInfo getEndpoints(String token, String url) {
        ReturnInfo returnInfo = iMruClient.getEndpoints(token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            List<RestEndpoint> list = JSON.parseArray(s, RestEndpoint.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 添加终端
     *
     * @param token
     * @param url
     * @param restEndpointReq
     * @return
     */
    @Override
    public ReturnInfo addEndpoint(String token, String url, RestEndpointReq restEndpointReq) {
        ReturnInfo returnInfo = iMruClient.addEndpoint(token, url, restEndpointReq);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestEndpoint list = JSON.parseObject(s, RestEndpoint.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 更新终端
     *
     * @param token
     * @param url
     * @param endpointId
     * @param restEndpointReq
     * @return
     */
    @Override
    public ReturnInfo updateEndpoint(String token, String url, String endpointId, RestEndpointReq restEndpointReq) {
        ReturnInfo returnInfo = iMruClient.updateEndpoint(token, url, endpointId, restEndpointReq);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestEndpoint list = JSON.parseObject(s, RestEndpoint.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 删除终端
     *
     * @param token
     * @param url
     * @param endpointId
     * @return
     */
    @Override
    public ReturnInfo deleteEndpoint(String token, String url, String endpointId) {
        ReturnInfo returnInfo = iMruClient.deleteEndpoint(token, url, endpointId);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map list = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    /**
     * 查询终端 TODO 未调用
     *
     * @param token
     * @param url
     * @param endpointId
     * @return
     */
    @Override
    public ReturnInfo getEndpoint(String token, String url, String endpointId) {
        ReturnInfo returnInfo = iMruClient.getEndpoint(token, url, endpointId);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestEndpoint list = JSON.parseObject(s, RestEndpoint.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }
}
