package org.cs.dp.sonar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;

import java.util.Map;

/**
 * @ClassName IServerFallback
 * @Description
 * @Author Liujt
 * @Date 2020/2/20 13:35
 **/
public class IServerFallback implements IServerClient {

    @Override
    public ReturnInfo getServer(Map<String, Integer> param) {
        return null;
    }
}
