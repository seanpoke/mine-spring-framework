package org.spring.introspect.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j(topic = "e")
@Component("a")
public class SpringBeanInfoTest {
	@Autowired
	AnnotationAutowiredFiledBeanTest autowiredFiledBeanTest;


	@Autowired
	public void setAnnotationAutowiredMethodBeanTest(AnnotationAutowiredMethodBeanTest methodBean){
		log.debug("AnnotationAutowiredMethodBeanTest=[{}]",methodBean);
	}

	public void setAutowiredInjectByTypeMethodBeanTest(AutowiredInjectByTypeMethodBeanTest injectBean){
		log.debug("AutowiredInjectByTypeMethodBeanTest=[{}]",injectBean);
	}

	public void setBeanDefinitionPropertyValuesBeanTest(BeanDefinitionPropertyValuesBeanTest propertyValuesBeanTest){
		log.debug("BeanDefinitionPropertyValuesBeanTest=[{}]",propertyValuesBeanTest);
	}

	//spring 注入
//	getC(){
//
//	}

	public void printf(){
		log.debug("autowiredFiledBeanTest=[{}]",autowiredFiledBeanTest);
	}

}
