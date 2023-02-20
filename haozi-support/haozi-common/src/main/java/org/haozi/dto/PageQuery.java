package org.haozi.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/17 10:48
 */
@Data
public class PageQuery<T> {

    /**
     * 页数
     */
    private Integer page;
    /**
     * 数量
     */
    private Integer size;

    private T query;

    public Map<String, Object> toParam() {
        Map<String, Object> map = null;
        if (query != null) {
            map = BeanUtil.beanToMap(this.query);
        } else {
            map = new HashMap<>();
        }

        if (page != null && size != null) {
            map.put(KEY_PAGE, page);
            map.put(KEY_SIZE, size);
        }
        return map;
    }

    private final String KEY_PAGE = "page";
    private final String KEY_SIZE = "size";
}
