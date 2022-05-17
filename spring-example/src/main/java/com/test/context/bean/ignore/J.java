package com.test.context.bean.ignore;

import com.test.context.bean.ignore.K;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/17
 **/
@Slf4j(topic = "e")
@Component
public class J {

	K k;

	public void setK(K k) {
		log.info("J setK");
	}
}
