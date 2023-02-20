package org.haozi.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/20 17:42
 */
@Data
public class DingMsg implements Serializable {


    /**
     * msgtype : text
     * text : {"content":"我就是我, 是不一样的烟火"}
     */

    private String msgtype;
    private TextBean text;

    @Data
    public static class TextBean implements Serializable {
        /**
         * content : 我就是我, 是不一样的烟火
         */

        private String content;
    }
}
