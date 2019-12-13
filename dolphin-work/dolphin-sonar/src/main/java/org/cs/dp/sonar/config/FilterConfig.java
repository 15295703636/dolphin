package org.cs.dp.sonar.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.UserInfo;
import org.cs.dolphin.common.constant.ModuleConstant;
import org.cs.dolphin.common.constant.RedisConstant;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.ExceptionUtil;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.common.redis.RedisUtil;
import org.cs.dp.ucenter.api.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName FilterConfig
 * @Description 过滤器，讲用户信息保存到 ThreadLocal 中
 * @Author Liujt
 * @Date 2019/11/21 15:13
 **/
@Slf4j
@Component
public class FilterConfig implements Filter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IUserClient iUserClient;

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getParameter("token");
        if (null == token) {
            token = request.getHeader("token");
        }
        //因为网关做了白名单过滤，业务模块不需要在做判断;获取到当前信息，存入到 ThreadLocal 中
        if (null != token) {
            ThreadLocalUserInfoUtil.set((UserInfo) redisUtil.getObject(RedisConstant.USER_TOKEN_PATH + token, UserInfo.class));
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            ReturnInfo returnInfo = null;
            log.error("Controller捕获未知异常，{}", e);
            try {
                iUserClient.allLog(new LogEntity(ModuleConstant.MODULE_UCENTER,
                        "error", "error,", request.getRemoteAddr(), request.getRequestURL().toString(),
                        ExceptionUtil.getStackTrace(e)
                ));
            } catch (Exception e2) {
                log.error("记录日志捕获未知异常，{}", e2);
            }
            if (e instanceof BaseException) {
                returnInfo = new ReturnInfo(MessageCode.COMMON_FAILURE_FLAG, ((BaseException) e).getDetailMessage());
            } else {
                returnInfo = new ReturnInfo(MessageCode.EXCEPTION, "系统繁忙，请稍后重试!");
            }

            PrintWriter writer = null;
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("application/json");
            try {
                writer = servletResponse.getWriter();
                writer.write(JSON.toJSONString(returnInfo));
                writer.flush();
            } catch (IOException ex) {
                log.error(ex.getMessage());
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    @Override
    public void destroy() {
        log.error("----------线程销毁----------");
    }
}
