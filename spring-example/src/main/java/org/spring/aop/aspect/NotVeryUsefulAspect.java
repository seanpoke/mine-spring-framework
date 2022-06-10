package org.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.spring.aop.service.DeleteService;
import org.spring.aop.service.impl.DeleteAServiceImpl;
import org.spring.aop.service.impl.DeleteBServiceImpl;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j(topic = "e")
public class NotVeryUsefulAspect {


	@DeclareParents(value="org.spring.aop.service.impl.UserServiceImpl", defaultImpl=DeleteBServiceImpl.class)
	public static DeleteService deleteService;

	@Pointcut("execution(* org.spring.aop.service..*.*(..))")
	public void pointCutPackage(){

	}

	@Pointcut("execution(* org.spring.aop.service..*.method4(..))")
	public void pointCutPackageMethod(){

	}



	@Pointcut("within(org.spring.aop.service.impl.ServiceImpl)")
	public void pointCutWithin(){

	}

	@Pointcut("within(org.spring.aop.service.impl.CglibTestService)")
	public void pointCutWithinCglibTestService(){

	}

	@Pointcut("args(Integer,String)")
	public void pointCutArgs(){

	}

	@Pointcut("this(org.spring.aop.service.impl.ServiceImpl)")
	public void pointCutThis(){

	}


	@Pointcut("target(org.spring.aop.service.Service)")
	public void pointCutTarget(){

	}

	@Pointcut("@args(org.spring.aop.anno.Aop)")
	public void pointCutAnnoArgs(){

	}

	@Pointcut("@within(org.spring.aop.anno.Aop)")
	public void pointCutAnnoWithin(){

	}

	@Pointcut("@annotation(org.spring.aop.anno.Aop)")
	public void pointCutAnnoAnnotation(){

	}






	@Before("pointCutAnnoAnnotation()")
	public void adviceBefore(){
		log.debug("before aop");
	}



	//-------------------------------------------------------//

	@Pointcut("within(org.spring.aop.service.impl.AAopServiceImpl)")
	public void pointCutWithinAAopService(){

	}

	@Pointcut("within(org.spring.aop.service.impl.BAopServiceImpl)")
	public void pointCutWithinBAopService(){

	}

	@Before("pointCutWithinAAopService()")
	public void adviceBeforeAAopService(){
		log.debug("=======before aop aservice========");

	}

	@After("pointCutWithinAAopService()")
	public void adviceAfterAAopService(){
		log.debug("=======after aop========");
	}

	@AfterReturning("pointCutWithinAAopService()")
	public void adviceAfterReturingAAopService(){
		log.debug("=======afterReturning aop========");
	}


	@Before("pointCutWithinBAopService()")
	public void adviceBeforeaBAopService(){
		log.debug("=======before aop bservice========");
	}
}
