package com.test.autoModel.dependsOn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@DependsOn("db")
@Component("da")
@Slf4j(topic = "e")
@DependsOn("db")
public class DA {

	@PostConstruct
	public void initMehtod(){
		log.debug("DA initMethod");
	}
}
