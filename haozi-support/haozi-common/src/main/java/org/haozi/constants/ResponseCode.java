package org.haozi.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 14:04
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAILURE(400);

    int code;


}
