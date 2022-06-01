package com.test.enhancer.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j(topic = "e")
public class ZSCallBack implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		log.debug("proxy logic");
		return methodProxy.invokeSuper(o,objects);
	}
}
