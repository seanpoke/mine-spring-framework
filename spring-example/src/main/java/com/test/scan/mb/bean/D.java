package com.test.scan.mb.bean;

import com.test.scan.mb.mapper.C;
import com.test.scan.mb.mapper.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/30
 **/
@Component
public class D {

	@Autowired
	C c;

	//@Autowired
	//E e;

	public void invoke(){
		c.doSql();
		//e.doSql();
	}
}
