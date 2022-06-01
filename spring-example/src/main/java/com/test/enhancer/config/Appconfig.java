package com.test.enhancer.config;

import com.test.enhancer.bean.X;
import com.test.enhancer.bean.Y;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan("com.test.enhancer.bean")
@Slf4j(topic = "e")
public class Appconfig {

	@Bean
	public X x(){
		log.debug("Appconfig#x() invoke");
		return  new X();
	}

	@Bean
	public Y y(){
		x();
		return  new Y();
	}

}
