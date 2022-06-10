package org.spring.beanPostProcessor.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.spring.beanPostProcessor.bean.BeanPostProcessorService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j(topic = "e")
//@Component
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor, InitializingBean {
	@Autowired
	BeanPostProcessorService beanPostProcessorService;

	@PostConstruct
	public void postConstruct(){
		log.debug("TestBeanFactoryPostProcessor PostConstruct init");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("TestBeanFactoryPostProcessor InitializingBean init");
	}


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.debug("TestBeanFactoryPostProcessor beanPostProcessorService[{}]",beanPostProcessorService);
	}


}
