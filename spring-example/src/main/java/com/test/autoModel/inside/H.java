package com.test.autoModel.inside;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 内部类的实例化现象
 * bean ---java对象
 * 1、java常识
 * 2、spring
 */
public class H {

	class J{
		public J(H h){

		}
	}
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(H.class);
		context.register(J.class);
		context.refresh();

		context.getBean(J.class);
	}
}
