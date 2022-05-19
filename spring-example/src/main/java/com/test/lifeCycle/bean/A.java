package com.test.lifeCycle.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
@Slf4j(topic = "e")
public class A implements BeanNameAware, ApplicationContextAware, InitializingBean {

	public A(){
		log.debug("1-A create constructor");
	}

	@Autowired
	B field;

	C c;

	public void m0(){
		log.debug("am0");
	}

	public void setC(C c) {
		log.debug("3-bytype wired:[{}]",c);
		this.c = c;
	}

	@PostConstruct
	public void postInit(){
		log.debug("6-PostConstruct init lifecycle");
	}


	@Override
	public void setBeanName(String name) {
		log.debug("4-setBeanName:[{}]",name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.debug("5-setApplicationContext:[{}]",applicationContext);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("7-afterPropertiesSet init lifecycle");
	}
}
