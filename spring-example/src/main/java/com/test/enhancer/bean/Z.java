package com.test.enhancer.bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "e")
public class Z {
	public Z(){
		log.debug("z create");
	}

	public void m0(){
		log.debug("-----===m0===========");
		log.debug("-----------end--------------------");
	}

	public void m1(){
		log.debug("-----===m1===========");
		log.debug("-----------end--------------------");
	}


	public void m2(){
		log.debug("-----===m2===========");
		log.debug("-----------end--------------------");
	}


}
