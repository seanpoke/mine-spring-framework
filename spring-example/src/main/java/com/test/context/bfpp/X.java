package com.test.context.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
public class X {
	public X(){
		log.debug("x bd create normal");
	}

	@Bean
	public Y y(){
		log.debug("x @bean create Y");
		return new Y();
	}
}
