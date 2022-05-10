package com.test.autoModel.order;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class T {


	List<E> beanFactoryPostProcessor;

	public List<E> getBeanFactoryPostProcessor() {
		return beanFactoryPostProcessor;
	}

	@Autowired
	public void setBeanFactoryPostProcessor(List<E> beanFactoryPostProcessor) {
		this.beanFactoryPostProcessor = beanFactoryPostProcessor;
	}
}
