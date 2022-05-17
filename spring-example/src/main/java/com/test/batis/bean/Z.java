package com.test.batis.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Z implements ApplicationContextAware {


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

	}
}
