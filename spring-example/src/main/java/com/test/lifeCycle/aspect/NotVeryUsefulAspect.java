package com.test.lifeCycle.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "e")
public class NotVeryUsefulAspect {
	@Pointcut("within(com.test.lifeCycle.bean.M)")
	private void pointcut() {}

	@Before("pointcut()")
	public void before(){
		log.debug("apo before");
	}
}
