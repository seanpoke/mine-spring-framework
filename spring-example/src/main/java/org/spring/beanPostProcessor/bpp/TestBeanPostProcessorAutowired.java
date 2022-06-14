package org.spring.beanPostProcessor.bpp;

import lombok.extern.slf4j.Slf4j;
import org.spring.beanPostProcessor.bean.BeanPostProcessorService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j(topic = "e")
//@Component("autowired")
public class TestBeanPostProcessorAutowired extends AutowiredAnnotationBeanPostProcessor {
	public  TestBeanPostProcessorAutowired(){
		log.debug("TestBeanPostProcessorAutowired create");
	}
	@PostConstruct
	public void postConstruct(){
		log.debug("TestBeanPostProcessorAutowired PostConstruct init");
	}

}
