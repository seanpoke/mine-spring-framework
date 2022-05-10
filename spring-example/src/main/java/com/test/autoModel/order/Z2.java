package com.test.autoModel.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Order(4)
@Component("2")
@Slf4j(topic = "1e")
public class Z2 {
	public Z2(){
		log.debug("order-{}",this.getClass().getAnnotation(Order.class).value());
	}

	@PostConstruct
	public void initMethod(){
		log.debug("annatation init bean z2");
	}
}
