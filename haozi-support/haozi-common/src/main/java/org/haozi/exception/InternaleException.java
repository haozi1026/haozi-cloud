package org.haozi.exception;

/**
 * 系统内部异常
 *
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 08:36
 */
public class InternaleException extends RuntimeException {

    private String msg;

    public InternaleException(String msg) {
        this.msg = "系统内部错误:" + msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
