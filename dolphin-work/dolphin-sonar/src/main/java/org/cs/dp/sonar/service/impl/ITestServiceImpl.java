package org.cs.dp.sonar.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.XxlJobBean;
import org.cs.dp.sonar.service.ITestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ClassName ITestServiceImpl
 * @Description 样例类
 * @Author Liujt
 * @Date 2019/11/21 17:51
 **/
@Slf4j
@Service
public class ITestServiceImpl implements ITestService {

    @Value("${xxl.job.admin.addresses}")
    private String xxlAddresses;

    @Value("${xxl.api.add.job.url}")
    private String xxlPath;

    /**
     * 添加定时任务
     *
     * @param param
     * @return
     */
    @Override
    public ReturnInfo addJob(XxlJobBean param) {
        param.setAuthor("test");
        param.setExecutorHandler("testJobHandler");
        param.setJobGroup(2);

        log.info("请求参数：{}", param);
        Map resMap = new RestTemplate().postForObject(xxlAddresses.concat(xxlPath), param, Map.class);
        log.info("返回参数：{}", resMap);

        return new ReturnInfo();
    }

}
