package org.cs.dp.radar.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.radar.api.entity.*;
import org.cs.dp.radar.service.IMruService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MruServiceImpl implements IMruService {
    @Resource(name = "facePlusRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public ReturnInfo createUt12(Ut12Entity ut12Entity) {
        Object param = null;
        Map resMap = new RestTemplate().postForObject("http://127.0.0.1:8080/mru/api/xxx", param, Map.class);
        return null;
    }

    public ResponseEntity<Object> restful(Object o, String url, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);
        HttpEntity<Object> entity = new HttpEntity<>(o, headers);
        return restTemplate.exchange(url, httpMethod, entity, Object.class);
    }

    @Override
    public ReturnInfo login(RestWebLoginReq restWebLoginReq, String url) {
//        HttpHeaders headers = new HttpHeaders();
//        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
//        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
//        // 请求体
//        headers.setContentType(mediaType);
//        HttpEntity<RestWebLoginReq> entity = new HttpEntity<>(restWebLoginReq, headers);
//        ResponseEntity<Object>  resp = restTemplate.exchange("http://"+url+"/api/rest/v2.0/web/login", HttpMethod.PUT,entity,Object.class);
        ResponseEntity<Object> resp = restful(restWebLoginReq, "http://" + url + "/api/rest/v2.0/web/login", HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo startConference(RestConfReq restConfReq, String token, String url) {
        ResponseEntity<Object> resp = restful(restConfReq, "http://" + url + "/api/rest/v2.0/conferences?token=" + token, HttpMethod.POST);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo getConferences(String token, String url, String startTime, String endTime) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/conferences?token=" + token + "&startTime=" + startTime + "&endTime=" + endTime, HttpMethod.GET);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo stopConference(String confId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/control/terminate?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo addPeer(RestPartyReq restPartyReq, String confId, String token, String url) {
        ResponseEntity<Object> resp = restful(restPartyReq, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/parties?token=" + token, HttpMethod.POST);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo removePeer(String confId, String partyId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/parties/" + partyId + "/control/delete?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo callPeer(String confId, String partyId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/parties/" + partyId + "/control/connect?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo hangupPeer(String confId, String partyId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/parties/" + partyId + "/control/disconnect?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo getConfInfo(String confId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "?token=" + token, HttpMethod.GET);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo switch2discussionMode(String confId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/control/switchToDiscussionMode?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo setLecturer(String confId, String partyId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/parties/" + partyId + "/control/setAsLecturer?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo startLiveStreaming(String confId, RestLiveStreamingReq restLiveStreamingReq, String token, String url) {
        ResponseEntity<Object> resp = restful(restLiveStreamingReq, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/control/startLiveStreaming?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo stopLiveStreaming(String confId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/control/stopLiveStreaming?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo muteAudioAll(String confId, boolean muteAudio, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/control/muteAudioAllParties?token=" + token + "&muteAudio=" + muteAudio, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo muteAudio(String peer, String confId, boolean muteAudio, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/parties/" + peer + "/control/muteAudio?token=" + token + "&muteAudio=" + muteAudio, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo getConferencePeers(String confId, String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/parties?token=" + token, HttpMethod.GET);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo setLivingStreamLayout(String confId, String token, String url, RestPartyLayout restPartyLayout) {
        ResponseEntity<Object> resp = restful(restPartyLayout, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/control/setRecordingLivingPeopleLayout?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo setPeerLayout(String confId, String peerId, String token, String url, RestPartyLayout restPartyLayout) {
        ResponseEntity<Object> resp = restful(restPartyLayout, "http://" + url + "/api/rest/v2.0/ongoingConferences/" + confId + "/parties/" + peerId + "/control/setLayout?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo getEndpoints(String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/endpoints?token=" + token, HttpMethod.GET);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo addEndpoint(String token, String url, RestEndpointReq restEndpointReq) {
        ResponseEntity<Object> resp = restful(restEndpointReq, "http://" + url + "/api/rest/v2.0/endpoints?token=" + token, HttpMethod.POST);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo updateEndpoint(String token, String url, String endpointId, RestEndpointReq restEndpointReq) {
        ResponseEntity<Object> resp = restful(restEndpointReq, "http://" + url + "/api/rest/v2.0/endpoints/" + endpointId + "?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo deleteEndpoint(String token, String url, String endpointId) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/endpoints/" + endpointId + "?token=" + token, HttpMethod.DELETE);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo getEndpoint(String token, String url, String endpointId) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/endpoints/" + endpointId + "?token=" + token, HttpMethod.GET);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo addUser(String token, String url, RestOrgUserReq restOrgUserReq) {
        ResponseEntity<Object> resp = restful(restOrgUserReq, "http://" + url + "/api/rest/v2.0/org/users?token=" + token, HttpMethod.POST);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo updateUser(String token, String url, String userId, RestOrgUserReq restOrgUserReq) {
        ResponseEntity<Object> resp = restful(restOrgUserReq, "http://" + url + "/api/rest/v2.0/org/users/" + userId + "?token=" + token, HttpMethod.PUT);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo getUsers(String token, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/users?token=" + token, HttpMethod.GET);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo getUser(String token, String userId, String url) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/users/" + userId + "?token=" + token, HttpMethod.GET);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }

    @Override
    public ReturnInfo deleteUser(String token, String url, String userId) {
        ResponseEntity<Object> resp = restful(null, "http://" + url + "/api/rest/v2.0/org/users/" + userId + "?token=" + token, HttpMethod.DELETE);
        if (200 > resp.getStatusCodeValue() || resp.getStatusCodeValue() > 206) {
            log.error("login error,error=" + resp.getBody().toString());
            return new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, "mru error", resp.getBody());
        }
        return ReturnInfo.ok(resp.getBody());
    }
}
