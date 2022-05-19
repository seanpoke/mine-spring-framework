package com.test.lifeCycle.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j(topic = "e")
@Component
public class D implements F{
	@Autowired
	E e;

	public E getE() {
		return e;
	}

	public void m0(){
		log.debug("m0-D");
	}
}
