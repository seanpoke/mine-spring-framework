package com.test.context.bean;

import com.test.context.event.ABeanInitEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Component
@Slf4j(topic = "e")
public class A implements InitializingBean {


	@Autowired
	C c;

	//@Autowired
	ApplicationContext context;

	public A(){
		log.debug("create a  Object");
	}
	//spring bean的生命周期初始化回调 注解形式
	@PostConstruct
	public void  init(){
		log.debug("a init PostConstruct");
		context.publishEvent(new ABeanInitEvent(context));
	}

	//spring bean的生命周期初始化回调 接口形式
	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("a init afterPropertiesSet");
	}

	//自动注入
	public void setC(C c) {
		log.debug("autowired C");
		this.c = c;
	}

	//获取
	public C getC() {
		log.debug("c:[{}]",c);
		return c;
	}
}
