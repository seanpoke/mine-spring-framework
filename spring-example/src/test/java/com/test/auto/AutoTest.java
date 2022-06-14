package com.test.auto;

import com.test.autoModel.util.ModelBeanFactoryPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.spring.introspect.config.App;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : shaonan.xiao
 * @date : 2022/6/13
 **/
@Slf4j(topic = "e")
public class AutoTest {

	@Test
	public void test(){
		AnnotationConfigApplicationContext
				context = new AnnotationConfigApplicationContext();

		context.register(App.class);
		context.refresh();
	}
}
