package org.cs.dp.ucenter.config;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ControllerExceptionHandle
 * @Description controller层统一异常处理
 * @Author Liujt
 * @Date 2019/11/19 17:14
 **/
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public ReturnInfo handleUserNotExistException(Exception ex) {
        log.error("Controller捕获统一异常：{}", ex);
        ReturnInfo resultInfo = new ReturnInfo();
        resultInfo.setMsg("系统繁忙，请稍后重试!");
        resultInfo.setReturnCode(500);
        return resultInfo;
    }
}
