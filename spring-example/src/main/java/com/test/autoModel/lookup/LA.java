package com.test.autoModel.lookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class LA {

	@Autowired
	ApplicationContext applicationContext;

	public void printInfo(){
		log.debug("lb-[{}]", applicationContext.getBean("LB ", LB.class));
		log.debug("lb-[{}]", applicationContext.getBean("LB", LB.class));
	}

}
