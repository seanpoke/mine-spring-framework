package com.test.lifeCycle.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/20
 **/
@Component
@Slf4j(topic = "e")
public class M {

	@Autowired
	N n;

	public void printM(){
		log.info("M print");
		n.print();
	}
}
