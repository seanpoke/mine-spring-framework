package com.test.context;


import com.test.context.bean.A;
import com.test.context.bean.ignore.F;
import com.test.context.config.ContextConfig;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class ContextTest {

	@Test
	public void defaultContext(){

		//bean工厂
		//DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		//
		//// 容器中加入A的beanDefinition
		//BeanDefinitionBuilder builderA = BeanDefinitionBuilder.genericBeanDefinition(A.class);
		//beanFactory.registerBeanDefinition("a",builderA.getBeanDefinition());
		//
		//// 容器中加入C的beanDefinition
		//BeanDefinitionBuilder builderC = BeanDefinitionBuilder.genericBeanDefinition(C.class);
		//beanFactory.registerBeanDefinition("c",builderC.getBeanDefinition());
		//
		//// 方案一：修改bean注入模式
		////builderA.getBeanDefinition().setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		//
		//// 方案二：注入BeanPostProcessor
		////AutowiredAnnotationBeanPostProcessor auto = new AutowiredAnnotationBeanPostProcessor();
		////auto.setBeanFactory(beanFactory);
		////beanFactory.addBeanPostProcessor(auto);
		//
		//// 实例化C
		//beanFactory.getBean(C.class);
		//// 实例化A
		//A a = beanFactory.getBean(A.class);
		//// 获取A中的c对象
		//a.getC();

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//context.addBeanFactoryPostProcessor(new B());
		//context.addBeanFactoryPostProcessor(new C());
		context.register(ContextConfig.class);
		context.refresh();
		System.out.println(context.getBean(F.class));


	}

	@Test
	public void xmlBeanFactoryScanContext(){
		//ClassPathXmlApplicationContext

		ClassPathResource classPathResource = new ClassPathResource("spring-context.xml");
		XmlBeanFactory beanFactory = new XmlBeanFactory(classPathResource);
		A a = beanFactory.getBean(A.class);
		a.getC();


//		AnnotationConfigApplicationContext
//				context = new AnnotationConfigApplicationContext(ContextConfig.class);


	}


	@Test
	public void ignoreContext(){
		AnnotationConfigApplicationContext
				context = new AnnotationConfigApplicationContext();

		context.scan("com.test.batis.bean");
		context.refresh();
		System.out.println(context.getBean(F.class).getK());
	}
}
