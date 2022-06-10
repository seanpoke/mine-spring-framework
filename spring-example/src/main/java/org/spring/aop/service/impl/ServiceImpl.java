package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.service.Service;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
@Component("service")
public class ServiceImpl implements Service {
	@Override
	public void method() {
		log.debug("general method");
	}

	@Override
	public void methodIntegerArgs(Integer i) {
		log.debug("methodIntegerArgs integer:[{}]",i);
	}

	@Override
	public void methodIntegerMultiArgs(Integer i, String s) {
		log.debug("methodIntegerMultiArgs integer:[{}] string[{}]",i,s);
	}


}
