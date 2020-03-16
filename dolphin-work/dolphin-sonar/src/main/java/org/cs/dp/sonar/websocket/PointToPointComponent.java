package org.cs.dp.sonar.websocket;

import lombok.extern.slf4j.Slf4j;
import org.cs.dp.sonar.common.SpringUtils;
import org.cs.dp.sonar.common.redis.PublishService;
import org.cs.dp.sonar.common.redis.SubscribeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName WebsocketController
 * @Description websocket 点对点(私聊)服务入口;应用场景:网站咨询，系统管理员回复
 * @Author Liujt
 * @Date 2019/12/3 11:48
 * <p>
 * ar url = "ws://127.0.0.1:9009/socketServer/websocket/point/token"; token最为 topic，用于私聊标志;
 * 用户咨询类信息，可以先做一个管理员通用页面，开通一个公用的topic，用户发消息后推送到公用的topic，点击连接，打开页面，向指定的topic发送消息
 * 聊天室：给新建的聊天室定义一个topic
 **/
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/point/{topic}")
@Order(value = 3)
public class PointToPointComponent {

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer = SpringUtils.getBean(RedisMessageListenerContainer.class);

    //存放该服务器该ws的所有连接。用处：比如向所有连接该ws的用户发送通知消息。
    private static CopyOnWriteArraySet<PointToPointComponent> sessions = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("topic") String topic) {
        log.info("java websocket:打开连接");
        this.session = session;
        sessions.add(this);
        SubscribeListener subscribeListener = new SubscribeListener();
        subscribeListener.setSession(session);
        //设置订阅topic
        redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic(topic));
    }

    @OnClose
    public void onClose(Session session) {
        log.info("java websocket:关闭连接");
        sessions.remove(this);
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("topic") String topic) throws IOException {
        log.info("{} websocket 收到消息=={}", topic, message);
        PublishService publishService = SpringUtils.getBean(PublishService.class);
        publishService.publish(topic, message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("java websocket 出现错误", error);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
