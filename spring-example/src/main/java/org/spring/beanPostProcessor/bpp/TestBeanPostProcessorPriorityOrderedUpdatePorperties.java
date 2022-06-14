package org.spring.beanPostProcessor.bpp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.spring.beanPostProcessor.bean.BeanPostProcessorService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
//@Component
@Slf4j(topic = "e")
public class TestBeanPostProcessorPriorityOrderedUpdatePorperties implements BeanPostProcessor, PriorityOrdered {
	@Autowired
	BeanPostProcessorService beanPostProcessorService;

//	public void printfInfo(){
//		log.debug("TestBeanPostProcessorPriorityOrderedUpdatePorperties beanPostProcessorService[{}]",beanPostProcessorService);
//	}
//
//	@PostConstruct
//	public void postConstruct(){
//		log.debug("TestBeanPostProcessorPriorityOrderedUpdatePorperties PostConstruct init");
//	}
	public TestBeanPostProcessorPriorityOrderedUpdatePorperties(){
		log.debug("==TestBeanPostProcessorPriorityOrderedUpdatePorperties create");
	}

	@SneakyThrows
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		if(beanName.equals("beanPostProcessorService")) {
//			Class<?> aClass = bean.getClass();
//			Field[] fs = aClass.getDeclaredFields();
//			for (Field f : fs) {
//				if(f.getName().equals("str")){
//					f.setAccessible(true);
//					f.set(bean,"spring");
//				}
//			}
//			return bean;
//		}
		return bean;
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	}
}
