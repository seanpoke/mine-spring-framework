package com.test.scan.mb.config;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.Set;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/30
 **/

public class MyClassPathMapperScanner extends ClassPathBeanDefinitionScanner {

	public MyClassPathMapperScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
		super(registry, useDefaultFilters);
	}

	@Override
	public void registerDefaultFilters(){
		addIncludeFilter((MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) ->
				metadataReader.getAnnotationMetadata().hasAnnotation(Mapper.class.getName()));
	}

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface();
	}

	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		// 扫描bd
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		GenericBeanDefinition definition;
		// 替换
		for (BeanDefinitionHolder holder : beanDefinitions) {
			definition = (GenericBeanDefinition) holder.getBeanDefinition();
			String beanClassName = definition.getBeanClassName();
			definition.setBeanClass(MyMapperFactoryBean.class);
			//definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
			definition.getPropertyValues().add("interfaceType",beanClassName);
		}
		return beanDefinitions;
	}
}
