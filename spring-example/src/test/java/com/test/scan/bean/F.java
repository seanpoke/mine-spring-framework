package com.test.scan.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j(topic = "e")
public class F {

	@PostConstruct
	public void  init(){
		log.debug("f init");
	}
}
