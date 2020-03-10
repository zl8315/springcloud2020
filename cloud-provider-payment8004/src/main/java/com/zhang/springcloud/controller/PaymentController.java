package com.zhang.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;
    /*@Resource
    private PaymentService paymentService;*/

    @RequestMapping(value = "/payment/zk")
    public String paymentzk() {
        return "springcloud with zookeeper, ServerPort : " + serverPort;
    }
}
