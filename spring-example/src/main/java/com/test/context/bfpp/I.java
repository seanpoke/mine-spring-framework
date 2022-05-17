package com.test.context.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class I implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.debug("i-s scan parent postProcessBeanFactory PriorityOrdered");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		log.debug("i-s scan subclass postProcessBeanDefinitionRegistry PriorityOrdered");
//		AbstractBeanDefinition j = (AbstractBeanDefinition) registry.getBeanDefinition("j");
//		j.setBeanClass(L.class);

//		BeanDefinitionBuilder x= BeanDefinitionBuilder.genericBeanDefinition(X.class);
//		registry.registerBeanDefinition("x",x.getBeanDefinition());

	}

	@Override
	public int getOrder() {
		return 0;
	}
}
