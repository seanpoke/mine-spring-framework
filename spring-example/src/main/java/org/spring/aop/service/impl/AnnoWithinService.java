package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.anno.Aop;
import org.springframework.stereotype.Component;

@Component
@Aop
@Slf4j(topic = "e")
public class AnnoWithinService {
	public void m(){
		log.debug("@aop");
	}
}
