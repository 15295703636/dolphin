package org.cs.dp.sonar.controller;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.common.redis.RedisUtil;
import org.cs.dp.sonar.domain.DeviceStateBean;
import org.cs.dp.sonar.service.IScheduleService;
import org.cs.dp.sonar.service.ISoBaioService;
import org.cs.dp.sonar.websocket.GroupSendComponent;
import org.cs.dp.sonar.websocket.PointToPointComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName TestWebsocketController
 * @Description 测试websocket
 * @Author Liujt
 * @Date 2019/12/3 14:19
 **/
@Slf4j
@RestController
@RequestMapping("testWebsocket")
@Api(tags = "测试websocket")
public class TestWebsocketController {

    @Autowired
    private GroupSendComponent groupWebsocket;

    @Autowired
    private PointToPointComponent ptpWebsocket;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISoBaioService iSoBaioService;
    @Autowired
    private IScheduleService iScheduleService;

    @GetMapping("group")
    public ReturnInfo testGroup(String str) throws IOException {
        log.error("-------------->str:{}", str);
        groupWebsocket.onMessage(null, str);
        return new ReturnInfo();
    }

    @GetMapping("PTP")
    public ReturnInfo testPTP(String topic, String message) throws IOException {
        log.error("-------------->str:{}", topic);
        ptpWebsocket.onMessage(null, message, topic);
        return new ReturnInfo();
    }

    @GetMapping("redisMapAdd")
    public ReturnInfo redisMapAdd(@RequestParam String key, @RequestParam String value) throws IOException {
        redisUtil.addMap("testMap", key, value);
        return new ReturnInfo();
    }

    @GetMapping("keepalive")
    public ReturnInfo keepalive() throws IOException {
        return iSoBaioService.keepalive("{\"status\":3,\"sn\":\"SXYTJYS20191126000025\"}",1);
    }

    @GetMapping("redisMapGet")
    public ReturnInfo redisMapGet(@RequestParam List<String> value) throws IOException {
        return new ReturnInfo(
                redisUtil.getMap("device:state", value, DeviceStateBean.class)
        );
    }

    @GetMapping("getByDeviceId")
    public ReturnInfo getByDeviceId(@RequestParam String sn) {
        return iScheduleService.getByDeviceId(sn);
    }
}
