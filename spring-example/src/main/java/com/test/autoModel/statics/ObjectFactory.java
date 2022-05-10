package com.test.autoModel.statics;

import org.springframework.stereotype.Component;

public class ObjectFactory {
	public  Object instanceObject(){
			return new A(2);
	}
}
