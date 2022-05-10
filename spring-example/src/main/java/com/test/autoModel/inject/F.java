package com.test.autoModel.inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class F {

	//@Autowired
	@Resource
	I a;//Ia

	@Resource
	G g;

	@Autowired
	L l;

	K k;

	public K getK() {
		return k;
	}

	public I getI() {
		return a;
	}

	public void setA(I a) {
		log.debug("a---{}",a);
	}
}
