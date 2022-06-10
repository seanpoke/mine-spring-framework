package org.spring.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.After;

@Slf4j(topic = "e")
public class AfterAdvice implements org.springframework.aop.AfterAdvice,MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		try {
			return invocation.proceed();
		}finally {
			log.debug("=========after custom=========");
		}

	}
	//为什么需要穿这个对象MethodInvocation
	//为了执行链上的下一个增强器

}
