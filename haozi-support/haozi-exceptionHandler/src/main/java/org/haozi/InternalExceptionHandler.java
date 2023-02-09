package org.haozi;

import org.haozi.dto.Response;
import org.haozi.exception.InternalException;
import org.haozi.exception.ParamEmptyException;
import org.haozi.util.ResponseBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/9 13:38
 */
@ControllerAdvice()
public class InternalExceptionHandler {
    @ExceptionHandler(InternalException.class)
    @ResponseBody
    public Response customHandler(ParamEmptyException paramEmptyException){
        return ResponseBuilder.fail(paramEmptyException.getMessage());
    }

}
