package com.test.scan;

import com.test.scan.config.ScanConfig;
import com.test.scan.inherited.PImpl;
import com.test.scan.inherited.S;
import com.test.scan.inherited.SImpl;
import com.test.scan.inherited.Sinherited;
import com.test.scan.mb.bean.D;
import com.test.scan.mb.config.MyScanConfig;
import com.test.scan.util.MyBeanNameGenerator;
import com.test.scan.util.MySpringInterfaceScaner;
import com.test.scan.util.ScanBeanDefinitionRegistyPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

@Slf4j(topic = "e")
public class ScanTest {

	@Test
	public void myScan(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyScanConfig.class);
		D d = applicationContext.getBean(D.class);
		d.invoke();
	}

	@Test
	public void defaultScan(){

		AnnotationConfigApplicationContext applicationContext = new
				AnnotationConfigApplicationContext();
		applicationContext.register(ScanConfig.class);
		//开发出来了  那就要用？
		applicationContext.setBeanNameGenerator(new MyBeanNameGenerator());
		applicationContext.refresh();


		applicationContext.scan("com.test.scan.bean");
		//invokeBeanFactoryPostProcessors --->执行ConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry
		//完成了扫描
		//applicationContext.refresh();
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		//applicationContext.setBeanNameGenerator();
//		applicationContext.addBeanFactoryPostProcessor(new ScanBeanDefinitionRegistyPostProcessor());
//		applicationContext.refresh();
	}

	/**
	 * 测试自己扩展的扫描器
	 * 过滤是否实现了某个接口 include
	 */
	@Test
	public void interfaceScan(){
		AnnotationConfigApplicationContext applicationContext = new
				AnnotationConfigApplicationContext();
		//没有传配置类 spring内置的扫描不会执行
		MySpringInterfaceScaner myScaner = new MySpringInterfaceScaner(applicationContext);
		myScaner.scan("com.test.scan.bean");
		applicationContext.refresh();
	}

	@Test
	public void inheritedScan(){
		Annotation[] annotations = Sinherited.class.getAnnotations();
		for (Annotation annotation : annotations) {
			log.debug("annotation:[{}]",annotation);
		}
		log.debug("讲道理只会打印一个IsInherited，因为他加了@Inherited，能被子类继承到");
		log.debug("---------------子类测试--------------------");

		System.out.println("\n\n");

		annotations = S.class.getAnnotations();
		for (Annotation annotation : annotations) {
			log.debug("annotation:[{}]",annotation);
		}
		log.debug("讲道理不会打印任何，因为接口子类不能继承父类接口，哪怕加了@Inherited");
		log.debug("---------------接口测试--------------------");

		System.out.println("\n\n");

		annotations = SImpl.class.getAnnotations();
		for (Annotation annotation : annotations) {
			log.debug("annotation:[{}]",annotation);
		}
		log.debug("讲道理不会打印任何，因为接口实现类无法继承，哪怕加了@Inherited");
		log.debug("---------------子类实现测试--------------------");


		System.out.println("\n\n");

		annotations = PImpl.class.getAnnotations();
		for (Annotation annotation : annotations) {
			log.debug("annotation:[{}]",annotation);
		}
		log.debug("讲道理不会打印任何，因为接口实现类无法继承，哪怕加了@Inherited");
		log.debug("---------------父类实现测试--------------------");
	}
}
