package org.haozi.dto;

import lombok.Data;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 14:01
 */
@Data
public class Response<T> {
    /**
     * 数据载体
     */
    private T Data;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 追踪id
     */
    private String traceId;
}
