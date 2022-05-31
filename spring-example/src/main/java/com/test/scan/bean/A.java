package com.test.scan.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@ComponentScan("com.test.scan.other")
@Component
@Slf4j(topic = "e")
public class A {

	@PostConstruct
	public void  init(){
		log.debug("a init");
	}


//	@Bean
//	public Z z(){
//		return  new Z();
//	}
}
