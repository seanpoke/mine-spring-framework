package com.test.context.bean.ignore;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/17
 **/
@Component
public class testIgnoreBFPP implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		((AbstractBeanDefinition) beanFactory.getBeanDefinition("f")).setAutowireMode(2);
		((AbstractBeanDefinition) beanFactory.getBeanDefinition("j")).setAutowireMode(2);


		// 自动注册模式下，忽略K属性注入，对所有bean都有效
		//beanFactory.ignoreDependencyType(K.class);

		// 针对接口中的set方法进行自动注入忽略
		beanFactory.ignoreDependencyInterface(IAware.class);
	}
}
