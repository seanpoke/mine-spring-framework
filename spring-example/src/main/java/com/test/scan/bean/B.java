package com.test.scan.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named
@Slf4j(topic = "e")
public class B {

	@PostConstruct
	public void  init(){
		log.debug("b init");
	}
}
