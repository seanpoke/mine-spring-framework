package com.test.batis.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class EBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		AbstractBeanDefinition e = (AbstractBeanDefinition) beanFactory.getBeanDefinition("e");
		e.setAutowireMode(2);

		AbstractBeanDefinition f = (AbstractBeanDefinition) beanFactory.getBeanDefinition("f");
		f.setAutowireMode(2);

		beanFactory.ignoreDependencyInterface(IAware.class);

		//beanFactory.ignoreDependencyType(K.class); 所有类都会忽略
		//beanFactory.ignoreDependencyInterface(IAware.class);
	}
}
