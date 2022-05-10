package com.test.autoModel.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j(topic = "1e")
@Order(6)
@Component("3")
public class Z3 {

	public Z3(){
		log.debug("order-{}",this.getClass().getAnnotation(Order.class).value());
	}

	@PostConstruct
	public void initMethod(){
		log.debug("annatation init bean z3");
	}
}
