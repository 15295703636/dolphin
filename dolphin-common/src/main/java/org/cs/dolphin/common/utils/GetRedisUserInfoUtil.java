package org.cs.dolphin.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.cs.dolphin.common.base.UserInfo;

import java.lang.reflect.Type;

/**
 * @ClassName GetRedisUserInfoUtil
 * @Description 获取Redis中缓存的用户信息
 * @Author Liujt
 * @Date 2019/11/21 14:51
 **/
public class GetRedisUserInfoUtil {
    public static UserInfo getRedisUserInfo(String token) {
        return JSONObject.parseObject(RedisUtil.get(token), UserInfo.class);
    }
}
