package com.test.context.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class D implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.debug("d-s scan parent postProcessBeanFactory");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		log.debug("d-s scan subclass postProcessBeanDefinitionRegistry register E F");
		BeanDefinitionBuilder e = BeanDefinitionBuilder.genericBeanDefinition(E.class);
		BeanDefinitionBuilder f= BeanDefinitionBuilder.genericBeanDefinition(F.class);


//		AbstractBeanDefinition j = (AbstractBeanDefinition) registry.getBeanDefinition("j");
//		j.setBeanClass(L.class);

		registry.registerBeanDefinition("e",e.getBeanDefinition());
		registry.registerBeanDefinition("f",f.getBeanDefinition());





	}
}
