package com.zhang.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id + "\t" + "haha";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")//设置3秒内走正常逻辑，超过3秒走兜底方法
    })
    public String paymentInfo_TimeOut(Integer id) {
        //int age = 10/0;
        //return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOut, id: " + id + "\tO(∩_∩)O哈哈~";
        int timeout = 4;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOut, id: " + id + "\t" + "耗时(秒)：" + timeout;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 系统繁忙或运行报错，请稍后再试, id: " + id + "\t o(╥﹏╥)o";
    }

    //======服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircutitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达多少后跳闸
    })

    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id 不能为负数");
        }
        return Thread.currentThread().getName() + "\t" + IdUtil.simpleUUID();
    }

    public String paymentCircutitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后再试o(╥﹏╥)o";
    }
}
