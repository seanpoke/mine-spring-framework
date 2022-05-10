package com.test.autoModel.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Order(6)
@Slf4j(topic = "e")
@Component
public class ABeanFactoryPostProcessor  extends E implements BeanFactoryPostProcessor{
	//实例化顺序  JVM对他的实例化
	public ABeanFactoryPostProcessor(){
		log.debug("==constructor bean a");
	}

	//把一个对象new出来之后 生命周期周期的一部
	//spring 自己调用
	@PostConstruct
	public void initMethod(){
		log.debug("annatation init bean a");
	}


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		int orderValue = 0;
		if(this.getClass().isAnnotationPresent(Order.class)){
			Order order = this.getClass().getAnnotation(Order.class);
			orderValue = order.value();
		}

		log.debug("execute postProcessBeanFactory a order={}",orderValue);
	}


}
