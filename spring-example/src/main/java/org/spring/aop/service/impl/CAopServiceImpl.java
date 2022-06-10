package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.service.AopService;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
@Component("c")
public class CAopServiceImpl implements AopService {
	@Override
	public void m() {
		log.debug("c m -BAopServiceImpl");
	}

	@Override
	public String m1(Integer i) {
		log.debug("c m1-BAopServiceImpl");
		return "m1-CAopServiceImpl";
	}
}
