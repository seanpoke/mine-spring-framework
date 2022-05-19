package com.test.lifeCycle.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class B {

	public B(){

	}

	public void m0(){
		log.debug("bm0");
	}
}
