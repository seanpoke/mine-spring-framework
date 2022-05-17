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
public class F implements IAware{

	K k;

	@Override
	public void setK(K k) {
		log.info("F setK");
	}

	public K getK() {
		return k;
	}
}
