package com.jjw.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.jjw.request.TestRequest;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @SentinelResource(value = "/get",blockHandler = "block",fallback = "fallback")
    public String test(TestRequest request) {
        return "Hello world";
    }


    public String block(TestRequest request,BlockException e){

        if(e instanceof FlowException){
            return "系统繁忙，限流了";
        }else if(e instanceof DegradeException){
            return "下游系统繁忙，降级了";
        }else{
            return e.getMessage();
        }

    }

    public String fallback(TestRequest request,Throwable e){

        return "进入fallback：" + e.getMessage();

    }

}
