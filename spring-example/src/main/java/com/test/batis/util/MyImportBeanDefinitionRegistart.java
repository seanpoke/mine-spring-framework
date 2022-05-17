package com.test.batis.util;

import com.test.batis.dao.AMapper;
import com.test.batis.dao.TMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j(topic = "e")
public class MyImportBeanDefinitionRegistart implements ImportBeanDefinitionRegistrar {
	Map<String,BeanDefinition> map= new HashMap<>();

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry){
		//模拟的mybatis 写死
		scan();
		for(String key : map.keySet()){
			AbstractBeanDefinition mapperBd = (AbstractBeanDefinition) map.get(key);
			Class mapperClz  =mapperBd.getBeanClass();
			log.debug("before:{}",mapperBd.getBeanClass().getName());
			mapperBd.setBeanClass(MyFactoryBean.class);
			log.debug("after:{}",mapperBd.getBeanClass().getName());
			mapperBd.getPropertyValues().add("mapperInterface",mapperClz.getName());
			registry.registerBeanDefinition(key,mapperBd);
		}
	}

	public void scan(){

		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(TMapper.class);
		map.put("tmapper",builder.getBeanDefinition());
		builder = BeanDefinitionBuilder.genericBeanDefinition(AMapper.class);
		map.put("amapper",builder.getBeanDefinition());
	}


}
