package com.test.context.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class C {
	public C(){
		log.debug("create c Object");
	}
}
