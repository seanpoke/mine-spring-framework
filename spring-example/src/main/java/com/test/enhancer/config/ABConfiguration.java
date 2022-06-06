package com.test.enhancer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : shaonan.xiao
 * @date : 2022/6/6
 **/
@Configuration
@ComponentScan("com.test.enhancer.bean")
@Slf4j(topic = "e")
public class ABConfiguration {

}
