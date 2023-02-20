package org.haozi.exception;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 11:07
 */
public class BizException extends InternalException {
    private String msg;
    public BizException(String msg){
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return "业务异常"+msg;
    }
}
