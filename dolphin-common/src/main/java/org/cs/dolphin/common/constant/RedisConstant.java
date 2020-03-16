package org.cs.dolphin.common.constant;

/**
 * @ClassName RedisConstant
 * @Description redis常量类
 * @Author Liujt
 * @Date 2019/11/25 15:46
 **/
public class RedisConstant {
    /**
     * 用户token路径
     */
    public static final String USER_TOKEN_PATH = "user:token:";
    /**
     * 设备/端状态心跳检测存储路径
     */
    public static final String DEVICE_KEEPALIVE_PATH = "state:device";
    /**
     * 流媒体状态心跳检测存储路径
     */
    public static final String BRS_KEEPALIVE_PATH = "state:brs";
    /**
     * websocket
     */
    public static final String WEB_SOCKET_PATH = "web:socket:";
    /**
     * 用户token有效时间
     */
    public static final long USER_TOKEN_EXPIRED_TIME = 1800;
}
