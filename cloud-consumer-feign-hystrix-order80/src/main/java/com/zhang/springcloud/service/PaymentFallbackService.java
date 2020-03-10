package com.zhang.springcloud.service;

import org.springframework.stereotype.Component;

//将Hystrix业务与异常处理分开
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back paymentInfo_OK, o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "-----PaymentFallbackService fall back paymentInfo_Timeout, o(╥﹏╥)o";
    }
}
