package com.test.scan.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public class ScanBeanDefinitionRegistyPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		try {
			MyScaner myScaner = new MyScaner();
			myScaner.scan("com.test.scan.bean");
			//完成了我们模拟的扫描

			List<String> listName = myScaner.getListName();
			Map<String, AbstractBeanDefinition> map = myScaner.getMap();
			//就会有值 符合规则  ab



			for (String s : listName) {
				registry.registerBeanDefinition(s,map.get(s));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
