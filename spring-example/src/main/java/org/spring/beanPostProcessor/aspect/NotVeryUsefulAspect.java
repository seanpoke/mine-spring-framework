package org.spring.beanPostProcessor.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.spring.aop.service.DeleteService;
import org.spring.aop.service.impl.DeleteBServiceImpl;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j(topic = "e")
public class NotVeryUsefulAspect {


	@Pointcut("within(org.spring.beanPostProcessor.bean.BeanPostProcessorService)")
	public void pointCutWithinService(){
	}




	@Before("pointCutWithinService()")
	public void adviceBefore(){
		log.debug("00000000000000-before aop-00000000000000");
	}



}
