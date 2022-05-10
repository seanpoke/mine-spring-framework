package com.test.autoModel.util;

//import com.test.autoModel.defaults.ExampleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

@Slf4j(topic = "e")
public class ModelBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition("m");

		log.debug("mode:{}",beanDefinition.getAutowireMode());
		//m.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

		//给我们A对象设置他的注入模型 =3
		//beanDefinition.setAutowireMode(3);
		//beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		//beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
		//a.getPropertyValues().add("exampleBean",beanFactory.getBean("exampleBean"));
		//beanFactory.ignoreDependencyType(ExampleBean.class);
	}
}
