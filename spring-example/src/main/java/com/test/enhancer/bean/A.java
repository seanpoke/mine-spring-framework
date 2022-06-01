package com.test.enhancer.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j(topic = "e")
public class A {
	public A(){
		log.debug("A create");
	}

	public void m0(){
		log.debug("-----===m0===========");
	}

	public void m1(){
		log.debug("-----===m1===========");
	}


	public void m2(){
		log.debug("-----===m2===========");
	}


}
