package com.test.autoModel.inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/10
 **/
@Component
public class Demo03 {

	@Resource
	private Demo01 demo01;

	@Resource
	private Demo02 demo02;
}
