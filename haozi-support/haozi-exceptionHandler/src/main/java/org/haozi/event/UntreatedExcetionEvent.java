package org.haozi.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/20 17:16
 */
public class UntreatedExcetionEvent extends ApplicationEvent {

    public UntreatedExcetionEvent(Exception exception,String traceId) {
        super(exception);
        this.exception = exception;
        this.traceId = traceId;
    }

    public Exception getException() {
        return exception;
    }

    public String getTraceId() {
        return traceId;
    }

    /**
     * 具体异常对象
     */
    private Exception exception;
    /**
     * 追踪id
     */
    private String traceId;

}
