package com.test.enhancer.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "e")
public class Y {
	public Y(){
		log.debug("y create");
	}
}
