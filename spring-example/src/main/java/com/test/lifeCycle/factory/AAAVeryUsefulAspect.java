package com.test.lifeCycle.factory;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "e")
public class AAAVeryUsefulAspect {
	@Pointcut("within(com.test.lifeCycle.factory.*)")
	private void pointcut() {}

	@Before("pointcut()")
	public void before(){
		log.debug("apo before");
	}
}
