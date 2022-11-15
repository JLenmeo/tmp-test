package com.jjw;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.PostConstruct;

/**
 * 网关配置
 *
 * @author: Jiang JiaWei
 * @date: 2022/11/14 21:38
 */
@Configuration
public class GatewayConfig {

    @PostConstruct
    public void init() {
        GatewayCallbackManager.setBlockHandler((serverWebExchange, throwable) -> {
            HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
            String msg = "sentinel异常";
            if (throwable instanceof FlowException) {
                msg = "请求被限流了";
            } else if (throwable instanceof ParamFlowException) {
                msg = "请求被热点参数限流了";
            } else if (throwable instanceof DegradeException) {
                msg = "请求被降级了";
            } else if (throwable instanceof SystemBlockException) {
                msg = "已触发系统保护";
            } else if (throwable instanceof AuthorityException) {
                msg = "没有权限访问";
                status = HttpStatus.UNAUTHORIZED;
            }

            JSONObject error = new JSONObject();
            error.put("code", status.value());
            error.put("msg", msg);

            return ServerResponse.status(status)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(error));
        });
    }

}
