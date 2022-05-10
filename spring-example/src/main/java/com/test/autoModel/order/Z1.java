package com.test.autoModel.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j(topic = "1e")
@Order(5)
@Component("1")
public class Z1 {

	public Z1(){
		log.debug("order-{}",this.getClass().getAnnotation(Order.class).value());
	}

	@PostConstruct
	public void initMethod(){
		log.debug("annatation init bean z1");
	}



}
