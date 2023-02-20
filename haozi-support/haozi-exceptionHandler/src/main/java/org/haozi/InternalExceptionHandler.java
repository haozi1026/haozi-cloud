package org.haozi;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.haozi.dto.Response;
import org.haozi.event.UntreatedExcetionEvent;
import org.haozi.exception.AccessException;
import org.haozi.exception.InternalException;
import org.haozi.exception.ParamEmptyException;
import org.haozi.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/9 13:38
 */
@ControllerAdvice()
@Slf4j
public class InternalExceptionHandler {

    /**
     * 系统内部交互参数异常
     * @param paramEmptyException
     * @return
     */
    @ExceptionHandler(InternalException.class)
    @ResponseBody
    public Response paramEmptyHandler(ParamEmptyException paramEmptyException){
        String traceId = IdUtil.fastSimpleUUID();
        log.error("系统内部调用参数异常,错误信息 {},traceId {}",paramEmptyException.getMessage(),traceId,paramEmptyException);
        return ResponseBuilder.fail(FRIENDLY_TIPS,traceId);
    }
    /**
     * 无权访问异常
     * @param accessException
     * @return
     */
    @ExceptionHandler(AccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response accessException(AccessException accessException){
        return ResponseBuilder.fail(accessException.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response untreatedExcetion(Exception exception){
        String traceId = IdUtil.fastSimpleUUID();
        String errorMsg = StrUtil.format("捕获到未处理异常,traceId{}",traceId);
        log.error(errorMsg,traceId,exception);
        SpringUtil.publishEvent(new UntreatedExcetionEvent(exception,traceId));
        return ResponseBuilder.fail(FRIENDLY_TIPS,traceId);
    }
    /**
     * 友好提示
     */
    private String FRIENDLY_TIPS = "系统内部异常，请稍后重试";
}
