package org.spring.beanPostProcessor.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j(topic = "e")
@Component("a")
public class BeanPostProcessorService {

	String str;
	Integer i;
	public BeanPostProcessorService(){
		log.debug("==BeanPostProcessorService create");
	}

	public Integer getI() {
		return i;
	}

	public String getStr() {
		return str;
	}

	public void testAop(){
		log.debug("--------BeanPostProcessorService logic-----");

	}


}
