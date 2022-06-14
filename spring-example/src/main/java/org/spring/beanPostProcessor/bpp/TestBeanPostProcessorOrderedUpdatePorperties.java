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

//@Component("ordered")
@Slf4j(topic = "e")
public class TestBeanPostProcessorOrderedUpdatePorperties implements BeanPostProcessor, Ordered {

	@Autowired
	BeanPostProcessorService beanPostProcessorService;

//	public void printfInfo(){
//		log.debug("TestBeanPostProcessorOrderedUpdatePorperties beanPostProcessorService[{}]",beanPostProcessorService);
//	}
//
//	@PostConstruct
//	public void postConstruct(){
//		log.debug("TestBeanPostProcessorOrderedUpdatePorperties PostConstruct init");
//	}

	public TestBeanPostProcessorOrderedUpdatePorperties(){
		log.debug("==TestBeanPostProcessorOrderedUpdatePorperties create");
	}

	@SneakyThrows
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		if(beanName.equals("beanPostProcessorService")) {
//			Class<?> aClass = bean.getClass();
//			Field[] fs = aClass.getDeclaredFields();
//			for (Field f : fs) {
//				Class<?> type = f.getType();
//				if(f.get(bean) instanceof String){
//					f.set(bean,f.get(bean)+"spring");
//				}
//			}
//			return bean;
//		}
		return null;
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	}
}
