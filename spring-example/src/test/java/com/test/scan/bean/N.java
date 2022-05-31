package com.test.scan.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
@Scope("prototype")
@Slf4j(topic = "e")
public class N {

	@PostConstruct
	public void  init(){
		log.debug("n init");
	}
}
