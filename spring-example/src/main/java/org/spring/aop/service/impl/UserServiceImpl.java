package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.service.UserService;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class UserServiceImpl implements UserService {

	@Override
	public void query() {
		log.debug("query");
	}
}
