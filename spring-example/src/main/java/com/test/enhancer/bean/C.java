package com.test.enhancer.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
@Import(E.class)
public class C {

	public C(){
		log.debug("c create ");
	}


}
