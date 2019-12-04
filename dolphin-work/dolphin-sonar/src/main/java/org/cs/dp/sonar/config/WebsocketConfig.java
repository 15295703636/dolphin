package org.cs.dp.sonar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName WebsocketConfig
 * @Description Websocket配置
 * @Author Liujt
 * @Date 2019/12/3 11:47
 **/
@Configuration
public class WebsocketConfig {
    /**
     * 支持websocket
     * 如果不使用内置tomcat，则无需配置
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter createServerEndExporter() {
        return new ServerEndpointExporter();
    }
}
