package com.test.lifeCycle.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if (beanName.equals("d")){
				return false;
			}
		return true;
	}
}
