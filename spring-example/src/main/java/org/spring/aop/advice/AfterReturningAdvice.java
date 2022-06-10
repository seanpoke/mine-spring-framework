package org.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

@Slf4j(topic = "e")
public class AfterReturningAdvice implements org.springframework.aop.AfterReturningAdvice {
//	@Override
//	public Object invoke(MethodInvocation invocation) throws Throwable {
//		Object proceed = invocation.proceed();
//		afterReturning(proceed,invocation.getMethod(),invocation.getArguments(),invocation.getThis());
//		return proceed;
//
//	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		log.debug("=========AfterReturning custom==returnValue[{}]=======",returnValue);
	}

	//为什么需要穿这个对象MethodInvocation
	//为了执行链上的下一个增强器

}
