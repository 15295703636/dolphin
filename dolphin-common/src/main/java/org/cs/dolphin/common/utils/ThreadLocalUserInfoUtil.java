package org.cs.dolphin.common.utils;

import org.cs.dolphin.common.base.UserInfo;

/**
 * @ClassName ThreadLocalUtil
 * @Description 保存当前线程的用户信息
 * @Author Liujt
 * @Date 2019/11/21 15:20
 **/
public class ThreadLocalUserInfoUtil {

    private ThreadLocalUserInfoUtil() {
    }

    private static final ThreadLocal<UserInfo> LOCAL = new ThreadLocal();

    public static void set(UserInfo user) {
        LOCAL.set(user);
    }

    public static UserInfo get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
