package com.test.scan.bean;

import com.test.scan.bean.Y;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 优先级落后于 spring内置的ConfigurationClassPostProcessor
 * 那么TestBeanDefinitionRegistyPostProcessor当中注册bd
 * 就不能作为一个配置类，只能作为一个普通类
 * Y能够正常被实例化但是Y里面的
 * @ComponentScan("com.test.scan.other") 无效--H不能被实例化
 * @BeanZ 失效
 */
@Slf4j(topic = "e")
@Component
public class TestBeanDefinitionRegistyPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		log.debug("postProcessBeanDefinitionRegistry execute");
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Y.class);
		registry.registerBeanDefinition("y",builder.getBeanDefinition());
	}
}
