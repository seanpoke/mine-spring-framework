package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.anno.Aop;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class AnnoAnnotationServiceNormal {
	public void m(){
		log.debug("normal");
	}
}
