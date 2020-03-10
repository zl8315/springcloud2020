package com.zhang.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {
    //Ribbon配置类不能和ComponentScan注解(即@SpringBootApplication)在同一目录或子目录
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
