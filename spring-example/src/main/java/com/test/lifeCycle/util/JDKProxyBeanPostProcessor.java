package com.test.lifeCycle.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@Slf4j(topic = "e")
public class JDKProxyBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		if("d".equals(beanName)){
			ClassLoader classLoader = this.getClass().getClassLoader();
			Class<?>[] interfaces = bean.getClass().getInterfaces();
			Object proxy = Proxy.newProxyInstance(classLoader, interfaces, new MyInvocationHandler(bean));
			return proxy;
		}
		return bean;
	}


	class MyInvocationHandler implements InvocationHandler {
		Object target;
		public MyInvocationHandler(Object target){
			this.target=target;
		}
		@Override
		public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
			log.debug("aop--before---");
			Object result = method.invoke(target, objects);
			log.debug("aop--after---");
			return null;
		}
	}

}
