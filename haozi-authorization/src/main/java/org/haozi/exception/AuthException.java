package org.haozi.exception;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 11:06
 */
public class AuthException extends BizException {

    private String msg;

    public AuthException(String msg) {
        super("认证时异常" + msg);
        this.msg = "认证时异常" + msg;

    }

    @Override
    public String getMessage() {
        return "认证时异常" + msg;
    }
}
