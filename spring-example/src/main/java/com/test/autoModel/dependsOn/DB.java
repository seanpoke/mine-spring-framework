package com.test.autoModel.dependsOn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("db")
@Slf4j(topic = "e")
public class DB {

	static {
		log.debug("static DB");
	}

	@PostConstruct
	public void initMehtod(){
		log.debug("DB initMethod");
	}
}
