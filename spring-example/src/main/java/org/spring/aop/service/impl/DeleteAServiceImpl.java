package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.service.DeleteService;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
public class DeleteAServiceImpl implements DeleteService {
	@Override
	public void delete() {
		log.debug("a delete");
	}
}
