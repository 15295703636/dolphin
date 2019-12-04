package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.websocket.GroupSendComponent;
import org.cs.dp.sonar.websocket.PointToPointComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
}
