package org.cs.dp.sonar.feign;

import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.api.feign.ISomqClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供radar服务中接收mq的回调
 */
@RestController
public class SomqClient implements ISomqClient {

    @Override
    @GetMapping(API_PREFIX+"/receivemsg")
    public ReturnInfo receiveMsg(@RequestParam String msg) {
        return null;
    }
}
