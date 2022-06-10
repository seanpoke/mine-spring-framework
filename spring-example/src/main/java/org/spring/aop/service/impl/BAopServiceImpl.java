package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.service.AopService;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
@Component("b")
public class BAopServiceImpl implements AopService {
	@Override
	public void m() {
		log.debug("b -BAopServiceImpl");
	}
}
