package org.spring.beanPostProcessor.bpp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 *
 */
@Slf4j(topic = "e")
//@Component
public class TestBeanPostProcessorNormalUpdatePorperties implements BeanPostProcessor {

	public TestBeanPostProcessorNormalUpdatePorperties(){
		log.debug("==TestBeanPostProcessorNormalUpdatePorperties create");
	}

	@SneakyThrows
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
		if(beanName.equals("a")) {
			Class<?> aClass = bean.getClass();
			Field[] fs = aClass.getDeclaredFields();
			for (Field f : fs) {
				if(f.getName().equals("str")){
					f.setAccessible(true);
					f.set(bean,"spring");
				}
			}
			return bean;
		}
		return null;
	}
}
