package com.test.enhancer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
@Slf4j(topic = "e")
public class MyCallBack implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		if (method.getName().equals("m0")){
			log.debug("m0相当于加了@Bean");
			log.debug("getBean  去获取一个bean");
		}else {
			methodProxy.invokeSuper(o, objects);
		}
		return null;
	}
}
