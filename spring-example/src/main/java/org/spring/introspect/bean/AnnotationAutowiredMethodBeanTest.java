package org.spring.introspect.bean;

import org.springframework.stereotype.Component;

@Component
public class AnnotationAutowiredMethodBeanTest {

	public AnnotationAutowiredMethodBeanTest(){
		System.out.println("构造了xxx");
	}

}
