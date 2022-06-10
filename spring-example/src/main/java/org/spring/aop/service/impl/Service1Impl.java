package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.service.Service;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
@Component("service1")
public class Service1Impl implements Service {
	@Override
	public void method() {
		log.debug("general method1");
	}

	@Override
	public void methodIntegerArgs(Integer i) {
		log.debug("methodIntegerArgs1 integer:[{}]",i);
	}

	@Override
	public void methodIntegerMultiArgs(Integer i, String s) {
		log.debug("methodIntegerMultiArgs1 integer:[{}] string[{}]",i,s);
	}


}
