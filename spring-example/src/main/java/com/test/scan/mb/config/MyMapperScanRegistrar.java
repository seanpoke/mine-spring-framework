package com.test.scan.mb.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/30
 **/

public class MyMapperScanRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {

		String value = String.valueOf(annotationMetadata.getAnnotationAttributes(MyMapperScan.class.getName()).get("value"));

		MyClassPathMapperScanner scanner = new MyClassPathMapperScanner(registry, true);

		scanner.scan(value);
	}
}
