package com.test.lifeCycle;

import com.test.enhancer.bean.A;
import com.test.enhancer.config.ABConfiguration;
import com.test.lifeCycle.config.LifeCycleConfig;
import com.test.lifeCycle.factory.M;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j(topic = "e")
public class CycleTest {

	@Test
	public void defaultCycle(){
		AnnotationConfigApplicationContext
				context
				 = new AnnotationConfigApplicationContext(ABConfiguration.class);

			context.getBean(A.class);
	}


	@Test
	public void cycle(){
		AnnotationConfigApplicationContext
				context
				= new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		context.getBean(M.class).printM();
	}
}
