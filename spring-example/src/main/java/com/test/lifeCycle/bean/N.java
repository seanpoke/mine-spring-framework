package com.test.lifeCycle.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Slf4j(topic = "e")
public class N implements InitializingBean {

	public N(){
		log.debug("n create ");
	}
	@Autowired
	M m;//半成品

	//N 初始化回调
	@Override
	public void afterPropertiesSet() throws Exception {
		m.printf();
	}
}
