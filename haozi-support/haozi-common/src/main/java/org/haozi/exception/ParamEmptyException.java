package org.haozi.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/9 09:28
 */
public class ParamEmptyException extends InternaleException {
    private String msg;
    public ParamEmptyException(String desc, String fieldName) {
        super("参数错误");
        this.msg =  StrUtil.format("参数为空 字段名为{}, 描述:{},",fieldName,desc);
    }
    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
