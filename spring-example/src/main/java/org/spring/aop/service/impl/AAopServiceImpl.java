package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.service.AopService;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
@Component("a")
public class AAopServiceImpl implements AopService {
	@Override
	public void m() {
		log.debug("a -AAopServiceImpl");
	}
}
