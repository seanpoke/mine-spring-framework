package com.test.autoMode;

import com.test.autoModel.defaults.M;
import com.test.autoModel.defaults.N;
import com.test.autoModel.inject.Demo03;
import com.test.autoModel.inject.F;
import com.test.autoModel.inject.I;
import com.test.autoModel.inject.K;
import com.test.autoModel.lookup.LA;
import com.test.autoModel.lookup.LC;
import com.test.autoModel.order.E;
import com.test.autoModel.order.T;
import com.test.autoModel.config.ModelConfig;
//import com.test.autoModel.defaults.A;
//import com.test.autoModel.defaults.ExampleBean;
import com.test.autoModel.statics.A;
import com.test.autoModel.statics.Config;
import com.test.autoModel.statics.ObjectFactory;
import com.test.autoModel.statics.SupplierFactory;
import com.test.autoModel.util.ModelBeanFactoryPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 测试注入模型
 */

@Slf4j(topic = "e")
public class ModelTest {

	/**
	 * 测试注入模型对bean的影响
	 * 包括实例化、或者一些高级特性
	 */
	@Test
	public void defaultModel(){
		AnnotationConfigApplicationContext
				context = new AnnotationConfigApplicationContext();
		context.register(ModelConfig.class);
		//改变了A的注入模型 spring当中的注入模型很重要
		context.register(ModelBeanFactoryPostProcessor.class);
		context.refresh();
		//N n = context.getBean(M.class).getN();
		//ExampleBean exampleBean = context.getBean(A.class).getExampleBean();
		//log.debug("n ==[{}]",n );
	}


	/**
	 * 测试@Autowired的注入基本原理
	 * 先type继而name
	 * 如果有多个再name
	 */
	@Test
	public void autowiredModel(){
		AnnotationConfigApplicationContext
				context = new AnnotationConfigApplicationContext();
		context.scan("com.test.autoModel.inject");
		context.refresh();
		//singletonObjects.get("f")
		//I i =context.getBean(F.class).getI();
		//log.debug("i==[{}]",i);

		//K k = context.getBean(F.class).getK();
		Demo03 demo03 = context.getBean(Demo03.class);
	}

	//
	///**
	// * 测试@Order注解的功能
	// * 不会影响bean的扫描顺序
	// * 具体需要看spring源码内部有没有对他做解析
	// */
	@Test
	public void orderModel(){
		AnnotationConfigApplicationContext
				context = new AnnotationConfigApplicationContext();
		context.scan("com.test.autoModel.order");
		context.refresh();
		List<E> beanFactoryPostProcessor = context.getBean(T.class).getBeanFactoryPostProcessor();
		for (E e : beanFactoryPostProcessor) {
			e.orderList();
		}
	}


	/**
	 * 测试工厂方法实例工厂方法、supplier
	 * spring优先选用supplier
	 */
	@Test
	public void staticsModel(){
		AnnotationConfigApplicationContext
				context = new AnnotationConfigApplicationContext();
		//context.scan("com.test.autoModel.statics");
//		context.register(Config.class);
		// 工厂bean
		context.register(ObjectFactory.class);
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		// 对象bean
		beanDefinition.setBeanClass(A.class);
		//beanDefinition.setFactoryBeanName("objectFactory");
		//beanDefinition.setFactoryMethodName("instanceObject");

		//不需要存在spring容器当中
		SupplierFactory supplierFactory = new SupplierFactory();
		//beanDefinition.setInstanceSupplier(SupplierFactory::getObject);
		beanDefinition.setInstanceSupplier(supplierFactory::instanceObject);

		// 将bd注册到容器中
		context.registerBeanDefinition("a",beanDefinition);
		context.refresh();
	}
//
//
//	/**
//	 * 测试lookup
//	 */
	@Test
	public void lookupModel(){
		AnnotationConfigApplicationContext
				context = new AnnotationConfigApplicationContext();
		context.scan("com.test.autoModel.lookup");
		context.refresh();
		context.getBean(LA.class).printInfo();

		//context.getBean(LC.class).printInfo();

	}
//
//
//	/**
//	 * 测试 dependsOn的作用
//	 */
//	@Test
//	public void dependsOnModel(){
//		AnnotationConfigApplicationContext
//				context = new AnnotationConfigApplicationContext();
//		context.scan("com.test.autoModel.dependsOn");
//		context.refresh();
//
//	}
}
