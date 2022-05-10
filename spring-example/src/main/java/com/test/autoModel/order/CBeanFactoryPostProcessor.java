package com.test.autoModel.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;

@Order(7)
@Slf4j(topic = "e")
@Component
public class CBeanFactoryPostProcessor extends E implements BeanFactoryPostProcessor, InitializingBean {
	public CBeanFactoryPostProcessor(){
		log.debug("==constructor bean c");
	}
	@PostConstruct
	public void initMethod(){
		log.debug("annatation init method bean c");
	}
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		int orderValue = 0;
		if(this.getClass().isAnnotationPresent(Order.class)){
			Order order = this.getClass().getAnnotation(Order.class);
			orderValue = order.value();
		}

		log.debug("execute postProcessBeanFactory c order={}",orderValue);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("InitializingBean init method bean c");
	}
}
