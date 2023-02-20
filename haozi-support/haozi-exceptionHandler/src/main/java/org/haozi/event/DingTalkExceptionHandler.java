package org.haozi.event;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.haozi.entity.DingMsg;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author zyh
 * @version 1.0
 * @date 2023/2/20 17:33
 */
@Slf4j
public class DingTalkExceptionHandler implements ApplicationListener<UntreatedExcetionEvent> {

    public DingTalkExceptionHandler(String accessToken){
        this.accessToken = accessToken;
    }
    @Override
    public void onApplicationEvent(UntreatedExcetionEvent event) {
        try {
            log.trace("监听到未捕获异常事件{}",event);
            String fullUrl = StrUtil.format(dingWebHookUrl, accessToken);
            DingMsg dingMsg = new DingMsg();
            DingMsg.TextBean textBean = new DingMsg.TextBean();

            String content = StrUtil.format("捕获到未处理异常,traceId {},异常信息{}",event.getTraceId(), ExceptionUtil.stacktraceToString(event.getException()));
            dingMsg.setMsgtype("text");
            textBean.setContent(content);
            dingMsg.setText(textBean);
            String jsonContent = JSONUtil.toJsonStr(dingMsg);
            log.trace("向钉钉发送消息{}",jsonContent);
            HttpUtil.post(fullUrl, jsonContent);
        } catch (Exception exception) {
            log.warn("向钉钉发送消息失败",exception);
        }
    }

    /**
     * 钉钉webhook token
     */
    private String accessToken;

    /**
     * 钉钉webhook地址
     */
    private String dingWebHookUrl  = "https://oapi.dingtalk.com/robot/send?access_token={}";


}
