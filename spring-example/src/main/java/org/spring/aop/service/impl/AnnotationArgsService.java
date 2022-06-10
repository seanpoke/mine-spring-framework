package org.spring.aop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.aop.anno.Aop;
import org.spring.aop.anno.MyParamter;
import org.spring.aop.anno.MyParamterAnno;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "e")
public class AnnotationArgsService {
	public void method1(MyParamter p){
		log.debug("m1p[{}]",p);
	}

	public void method2(MyParamterAnno p){
		log.debug("m2p[{}]",p);
	}

	public void method3(MyParamterAnno p,Integer i){
		log.debug("m3p-i[{}]",p);
	}

	public void method4(MyParamterAnno p){
		log.debug("m4p[{}]",p);
	}


}
