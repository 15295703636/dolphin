package org.cs.dp.ucenter.config;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.utils.GetRedisUserInfoUtil;
import org.cs.dolphin.common.utils.RedisUtil;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName FilterConfig
 * @Description 过滤器，讲用户信息保存到 ThreadLocal 中
 * @Author Liujt
 * @Date 2019/11/21 15:13
 **/
@Slf4j
@Component
public class FilterConfig implements Filter {
    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = httpServletRequest.getHeader("token");
        //因为网关做了白名单过滤，业务模块不需要在做判断;获取到当前信息，存入到 ThreadLocal 中
        if (null != token) {
            ThreadLocalUserInfoUtil.set(GetRedisUserInfoUtil.getRedisUserInfo(RedisUtil.USER_TOKEN_PATH + token));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.error("----------线程销毁----------");
    }
}
