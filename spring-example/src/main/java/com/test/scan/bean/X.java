package com.test.scan.bean;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j(topic = "e")
public class X {
	@PostConstruct
	public void  init(){
		log.debug("x init");
	}
}
