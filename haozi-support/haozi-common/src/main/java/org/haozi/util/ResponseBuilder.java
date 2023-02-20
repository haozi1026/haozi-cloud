package org.haozi.util;

import org.haozi.constants.ResponseCode;
import org.haozi.dto.Response;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 14:08
 */
public class ResponseBuilder {

    /**
     * 成功对象
     * @param data
     * @return
     */
    public static Response success(Object data){
        Response response = new Response();
        response.setData(data);
        response.setCode(ResponseCode.SUCCESS.getCode());
        return response;
    }

    /**
     * 失败对象
     * @param msg
     * @return
     */
    public static Response fail(String msg){
        Response response = new Response();
        response.setErrorMsg(msg);
        response.setCode(ResponseCode.FAILURE.getCode());
        return response;
    }

    /**
     * 失败对象
     * @param msg 错误信息
     * @param traceId 追踪id
     * @return
     */
    public static Response fail(String msg,String traceId){
        Response response = new Response();
        response.setErrorMsg(msg);
        response.setTraceId(traceId);
        response.setCode(ResponseCode.FAILURE.getCode());
        return response;
    }

}
