package com.test.autoModel.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
public class E {
	public void orderList(){
		int orderValue = 0;
		if(this.getClass().isAnnotationPresent(Order.class)){
			Order order = this.getClass().getAnnotation(Order.class);
			orderValue = order.value();
		}
		log.debug("List Order postProcessBeanFactory {} order={}",this.getClass().getSimpleName(),orderValue);
	}
}
