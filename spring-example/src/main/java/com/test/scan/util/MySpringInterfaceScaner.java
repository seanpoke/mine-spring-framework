package com.test.scan.util;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.TypeFilter;

public class MySpringInterfaceScaner extends ClassPathBeanDefinitionScanner {

	public MySpringInterfaceScaner(BeanDefinitionRegistry registry) {
		super(registry);
	}


	@Override
	protected void registerDefaultFilters() {
			addIncludeFilter(new InterFaceFilter(false,true));
	}

	@Override
	public void addIncludeFilter(TypeFilter includeFilter) {
		super.addIncludeFilter(includeFilter);
	}
}
