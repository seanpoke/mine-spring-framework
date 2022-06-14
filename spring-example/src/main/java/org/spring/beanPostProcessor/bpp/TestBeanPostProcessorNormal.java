package org.spring.beanPostProcessor.bpp;

import lombok.extern.slf4j.Slf4j;
import org.spring.beanPostProcessor.bean.BeanPostProcessorService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j(topic = "e")
@Component("normal")
public class TestBeanPostProcessorNormal implements BeanPostProcessor, InitializingBean {

//	@PostConstruct
//	public void postConstruct(){
//		log.debug("TestBeanPostProcessorNormal PostConstruct init");
//	}

	@Autowired
	BeanPostProcessorService beanPostProcessorService;

	public TestBeanPostProcessorNormal(){
		log.debug("==TestBeanPostProcessorNormal create");
	}

	public void printfInfo(){
		//log.debug("TestBeanPostProcessorNormal beanPostProcessorService[{}]",beanPostProcessorService);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//log.debug("TestBeanPostProcessorNormal InitializingBean init");
	}
}
