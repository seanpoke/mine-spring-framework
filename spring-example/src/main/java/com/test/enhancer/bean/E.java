package com.test.enhancer.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j(topic = "e")
public class E {

	public E(){
		log.debug("e create ");
	}

}
