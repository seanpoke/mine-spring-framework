package com.test.context.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class A implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		BeanDefinitionBuilder k = BeanDefinitionBuilder.genericBeanDefinition(K.class);
//
//		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
//		defaultListableBeanFactory.registerBeanDefinition("k",k.getBeanDefinition());

		BeanDefinitionBuilder x = BeanDefinitionBuilder.genericBeanDefinition(X.class);

		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
		defaultListableBeanFactory.registerBeanDefinition("x",x.getBeanDefinition());
		log.debug("a-p scan parent postProcessBeanFactory");
	}
}
