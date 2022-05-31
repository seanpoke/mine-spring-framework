package com.test.scan.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@ComponentScan("com.test.scan.other")
@Slf4j(topic = "e")
public class Y {

	@PostConstruct
	public void  init(){
		log.debug("y init");
	}


	@Bean
	public Z z(){
		return  new Z();
	}
}
