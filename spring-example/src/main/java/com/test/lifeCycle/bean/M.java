package com.test.lifeCycle.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Slf4j(topic = "e")
public class M {
	@Autowired
	N n;
	public M(){
		log.debug("m create ");
	}

	public void printf(){
		log.debug("printf");
	}
}
