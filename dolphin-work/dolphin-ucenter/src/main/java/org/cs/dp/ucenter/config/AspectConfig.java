package org.cs.dp.ucenter.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.cs.dolphin.common.base.ParamValid;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.constant.AspectConstant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LogAspect
 * @Description 统一日志打印，参数校验
 * @Author Liujt
 * @Date 2019/11/25 9:37
 **/
@Slf4j
@Aspect
@Component
public class AspectConfig {

    @Pointcut(AspectConstant.USER_SERVER)
    public void webLog() {
    }

    @Around("webLog()")
    public Object Interceptor(ProceedingJoinPoint point) throws Throwable {
        Long startTime = System.currentTimeMillis();

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Object returnObj = null;
        // 记录下请求内容
        log.info("统一日志记录URL:{} ", request.getRequestURL().toString());
        log.info("统一日志记录HTTP_METHOD: {} ", request.getMethod());
        log.info("统一日志记录IP: {} ", request.getRemoteAddr());
        log.info("统一日志记录CLASS_METHOD : {} ", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        Object obj[] = point.getArgs();
        if (!"GET".equals(request.getMethod())
                && !request.getHeader("content-type").contains("multipart")
                && obj.length > 0
                && !request.getRequestURL().toString().contains("loginOut")) {
            log.info("统一日志记录PARAM : {} ", JSON.toJSONString(obj[0]));
        }

        try {
            // 获取切入的 Method
            MethodSignature joinPointObject = (MethodSignature) point.getSignature();
            Method method = joinPointObject.getMethod();
            if (method.isAnnotationPresent(ParamValid.class)) {
                BindingResult result = (BindingResult) point.getArgs()[1];
                ReturnInfo errorMap = this.validRequestParams(result);
                if (errorMap != null) {
                    returnObj = errorMap;
                } else {
                    returnObj = point.proceed();
                }
            } else {
                returnObj = point.proceed();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // 处理完请求，返回内容
            log.info("统一日志记录RESPONSE : {}", returnObj);
            log.info("统一日志记录SPEND TIME : {}", (System.currentTimeMillis() - startTime));
        }
        return returnObj;
    }

    /**
     * 校验
     */
    private ReturnInfo validRequestParams(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<String> lists = new ArrayList<>();
            for (ObjectError objectError : allErrors) {
                lists.add(objectError.getDefaultMessage());
            }
            return new ReturnInfo(HttpStatus.BAD_REQUEST.value(), "入参校验失败", lists);
        }
        return null;
    }
}
