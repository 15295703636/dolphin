package org.cs.dp.sonar.websocket;

import lombok.extern.slf4j.Slf4j;
import org.cs.dp.sonar.common.Constant;
import org.cs.dp.sonar.common.SpringUtils;
import org.cs.dp.sonar.common.redis.PublishService;
import org.cs.dp.sonar.common.redis.SubscribeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName WebsocketController
 * @Description websocket 发布订阅模式(群发)服务入口;应用场景:平台管理员发布消息(固定的topic)
 * @Author Liujt
 * @Date 2019/12/3 11:48
 **/
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/groupSend")
public class GroupSendComponent {

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer = SpringUtils.getBean(RedisMessageListenerContainer.class);

    //存放该服务器该ws的所有连接。用处：比如向所有连接该ws的用户发送通知消息。
    private static CopyOnWriteArraySet<GroupSendComponent> sessions = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        log.info("java websocket:打开连接");
        this.session = session;
        sessions.add(this);
        SubscribeListener subscribeListener = new SubscribeListener();
        subscribeListener.setSession(session);
        //设置订阅topic
        redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic(Constant.WEBSOCKET_TOPIC));
    }

    @OnClose
    public void onClose(Session session) {
        log.info("java websocket:关闭连接");
        sessions.remove(this);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("java websocket 收到消息==" + message);
        PublishService publishService = SpringUtils.getBean(PublishService.class);
        publishService.publish(Constant.WEBSOCKET_TOPIC, message);
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
