package com.test.scan.mb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/30
 **/
@Slf4j(topic = "e")
public class MyMapperFactoryBean<T> implements FactoryBean<T> , InvocationHandler {

	private Class<T> interfaceType;

	public void setInterfaceType(Class<T> interfaceType) {
		this.interfaceType = interfaceType;
	}



	@Override
	public T getObject() throws Exception {
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { interfaceType },this);
	}

	@Override
	public Class<T> getObjectType() {
		return interfaceType;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(this, args);
		}else {
			log.info("===假装执行方法结束===");
			log.info("===假装执行方法中===");
			log.info("===假装执行方法结束===");
		}
		return null;
	}
}
