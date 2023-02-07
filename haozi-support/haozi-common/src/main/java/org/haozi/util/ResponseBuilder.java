package org.haozi.util;

import org.haozi.constants.ResponseCode;
import org.haozi.dto.Response;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/7 14:08
 */
public class ResponseBuilder {

    public static Response success(Object data){
        Response response = new Response();
        response.setData(data);
        response.setCode(ResponseCode.SUCCESS.getCode());
        return response;
    }
    public static Response fail(Object data){
        Response response = new Response();
        response.setData(data);
        response.setCode(ResponseCode.FAILURE.getCode());
        return response;
    }
}
