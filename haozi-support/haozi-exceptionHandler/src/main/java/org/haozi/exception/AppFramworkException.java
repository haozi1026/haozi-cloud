package org.haozi.exception;

/**
 * 应用框架异常
 * @author zyh
 * @version 1.0
 * @date 2023/2/6 20:29
 */
public class AppFramworkException extends InternalException {

    private String errorMsg;
    public AppFramworkException(String msg){
        super(msg);
        this.errorMsg = msg;
    }

    @Override
    public String getMessage() {
        return "应用框架错误" + errorMsg;
    }
}
