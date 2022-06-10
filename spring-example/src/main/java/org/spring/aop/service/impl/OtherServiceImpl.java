package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.service.Service;
import org.spring.aop.service.ServiceOther;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class OtherServiceImpl implements ServiceOther {

	@Override
	public void methodOther() {
		log.debug("other");
	}
}
