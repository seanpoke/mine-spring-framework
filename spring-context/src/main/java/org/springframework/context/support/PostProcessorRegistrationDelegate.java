/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;

/**
 * Delegate for AbstractApplicationContext's post-processor handling.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 4.0
 */
final class PostProcessorRegistrationDelegate {

	private PostProcessorRegistrationDelegate() {
	}


	/**
	 *
	 *	BeanDefinitionRegistryPostProcessor接口的父接口为BeanFactoryPostProcessor接口
	 *
	 *  父类：直接实现了BeanFactoryPostProcessor接口的类
	 *  父类方法：BeanFactoryPostProcessor#postProcessBeanFactory()
	 *
	 *  子类：直接实现了BeanDefinitionRegistryPostProcessor接口的类
	 *  子类方法：BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry()
	 *
	 *  ccpp:ConfigurationClassPostProcessor
	 *
	 *  执行顺序：
	 *  	一级分类：子类方法 ---> 父类方法
	 *  	二级分类：外部api ---> dbm
	 *
	 *
	 * 		     外部子类的子方法--》内部子类的子方法--》所有子类的父方法--》外部父类的父方法--》内部父类的父方法
	 *
	 *  0、BeanDefinitionRegistryPostProcessor与ImportBeanDefinitionRegistrar的区别
	 *  	。ImportBeanDefinitionRegistrar回调方法中可以获取到注解信息，比如@MapperScan。
	 * 		. ImportBeanDefinitionRegistrar执行时机早于BeanDefinitionRegistryPostProcessor（除了外部api提供的）
	 * 		. BeanDefinitionRegistryPostProcessor（除了外部api提供的）对一些bean的注册可能有部分功能失效，比如@bean注解
	 * 		. ImportBeanDefinitionRegistrar没有上述问题，因为它在cccpp内部执行
	 * 		. 如果要动态注册bd，建议使用ImportBeanDefinitionRegistrar
	 *
	 *  1、为什么要先执行外部api的后执行bdm内置的？--- 保证外部api子类的子类方法中产生的对象bd在内置bdm扫描开始之前添加进去。（ccpp会对bd的@bean注解等进行解析），防止出现残次bean
	 *
	 *  2、子类每次都重新从bdm中根据类型重新获取，而父类只根据类型从bdm获取一次？---子类中存在动态注册beanDefinition的行为，所以bdm是动态变化的，而父类中不会存在注册的行为
	 *
	 *  3、在扫描父类的时候为什么对于扫描出来的高优先级父类要直接实例化？--- 如果其他父类扫描出来的时候同时也实例化，那么在高优先级的父类中修改其他父类时，会导致修改无法生效。道理同下。
	 *  	。对于同批次扫描出来的父类，仅高优先级的父类【扫描出来后就直接实例化】再统一执行父类方法，其他父类扫【仅描出来】后续统一再实例化并执行父类方法。
	 *
	 *  4、在BeanDefinitionRegistryPostProcessor中修改bd的局限性---提高子类的等级，比如实现priorityOrdered接口，保证D在J之前被扫描并实例化
	 *  	。对于同批次扫描出来的子类，【扫描出来后就直接实例化】再统一执行子类方法。相互之间修改对方的beanClass等属性会不生效。
	 *
	 *  5、processedBeans为扫描不需要加入外部api的？--- 外部的api存储在List<BeanFactoryPostProcessor>中，而processedBeans存储的为bdm中扫描出的，而bdm是动态是动态变化的
	 *
	 *  6、BeanFactoryPostProcessor中为什么不开发注册bd（可以强转完成）？---可能不会注册或注册残次的bean。
	 */
	public static void invokeBeanFactoryPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {

		// Invoke BeanDefinitionRegistryPostProcessors first, if any.

		// 不会存在API提供的
		// 存放已经执行完毕的BeanFactoryPostProcessor以及其子类BeanDefinitionRegistryPostProcessor
		// 防止重复执行
		Set<String> processedBeans = new HashSet<>();

		if (beanFactory instanceof BeanDefinitionRegistry) {
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
			// 存储直接实现了父类BeanFactoryPostProcessor类型bean
			List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
			// 存储直接实现了子类BeanDefinitionRegistryPostProcessor类型bean
			List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();

			/**
			 * 1、对外部api注入的BeanFactoryPostProcessor进行分类
			 * 2、执行对外部api子类的子类方法
			 */
			for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
				if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
					BeanDefinitionRegistryPostProcessor registryProcessor =
							(BeanDefinitionRegistryPostProcessor) postProcessor;
					// 回调子类方法
					registryProcessor.postProcessBeanDefinitionRegistry(registry);
					// add子类
					registryProcessors.add(registryProcessor);
				}
				else {
					// add父类
					regularPostProcessors.add(postProcessor);
				}
			}

			// Do not initialize FactoryBeans here: We need to leave all regular beans
			// uninitialized to let the bean factory post-processors apply to them!
			// Separate between BeanDefinitionRegistryPostProcessors that implement
			// PriorityOrdered, Ordered, and the rest.
			// 当前要执行的子类
			List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

			/**
			 * 第1次遍历
			 * 1、获取bdm中的子类
			 * 2、执行【优先级排序子类】的子类方法
			 * 作用：ccpp执行注解扫描，将扫描到的类加入到BeanDefinitionMap
			 */
			// First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
			// 通过类型去beanDefinitionMap中查询子类，返回beanName
			// 可以找到之前内置的 [ccpp],   ccpp简写ConfigurationClassPostProcessor
			String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				// 是否实现了PriorityOrdered接口
				if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) { // true
					// 实例化并且放入到当前要执行的集合中
					// 这里，beanFactory.getBean会去实例化ccpp 存储到单例池中。 添加ccpp到【当前子集合】中
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					// 表示已经处理完了。这里，ccpp添加到【过期集合】中
					processedBeans.add(ppName);
				}
			}
			// 排序。这里，目前只有一个无需排序
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			// 合并，为了将来执行父类方法。这里，将ccpp添加到【子集合】中
			registryProcessors.addAll(currentRegistryProcessors);
			// 执行【当前子集合】中所有对象的的子类方法postProcessBeanDefinitionRegistry。这里，执行ccpp的子类方法，完成扫描并加入到beanDefinitionMap中。
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			// 清空【当前子集合】
			currentRegistryProcessors.clear();

			/**
			 * 第2次遍历
			 * 1、获取bdm中的子类
			 * 2、执行【排序子类】的子类方法
			 * 原因：由于第一次扫描可能新增了许多新的BeanDefinitionRegistryPostProcessors
			 */
			// Next, invoke the BeanDefinitionRegistryPostProcessors that implement Ordered.
			// 通过类型去beanDefinitionMap中查询子类，返回beanName   [ccpp,D,I]
			postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				// 未执行过且实现了order接口
				if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					processedBeans.add(ppName);
				}
			}
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			registryProcessors.addAll(currentRegistryProcessors);
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			currentRegistryProcessors.clear();


			/**
			 * 第3+次遍历，
			 * 1、获取bdm中的子类
			 * 2、执行【子类】的子类方法
			 * 原因：
			 * 1、第二遍的扫描存在判断条件，beanFactory.isTypeMatch(ppName, Ordered.class)，所以可能存在没有执行的BeanDefinitionRegistryPostProcessors。
			 * 2、每次执行子类方法的过程中，可能存在手动添加了BeanDefinitionRegistryPostProcessors类型的BeanDefinition
			 *
			 */
			// Finally, invoke all other BeanDefinitionRegistryPostProcessors until no further ones appear.
			boolean reiterate = true;
			while (reiterate) {
				reiterate = false;
				postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
				for (String ppName : postProcessorNames) {
					if (!processedBeans.contains(ppName)) {
						currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
						processedBeans.add(ppName);
						reiterate = true;
					}
				}
				sortPostProcessors(currentRegistryProcessors, beanFactory);
				registryProcessors.addAll(currentRegistryProcessors);
				invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
				currentRegistryProcessors.clear();
			}

			/**
			 * 1、执行所有子类的父类方法（包括外部api和bdm）
			 * 2、执行外部api父类的父类方法
			 */
			// Now, invoke the postProcessBeanFactory callback of all processors handled so far.
			// 执行所有子类BeanDefinitionRegistryPostProcessor的父类方法postProcessBeanFactory
			invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
			// 执行外部api父类型BeanFactoryPostProcessor的方法postProcessBeanFactory
			invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
		}

		else {
			// Invoke factory processors registered with the context instance.
			invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
		}



		/**
		 *
		 * 1、获取bdm中的父类
		 * 2、对父类进行分类【优先级排序父类、排序父类、普通父类】
		 * 3、依次遍历执行父类的父类方法
		 */
		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let the bean factory post-processors apply to them!
		// 通过类型去beanDefinitionMap中查询父类，返回beanName
		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

		// Separate between BeanFactoryPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		// 实现了 priorityOrdered的父类集合
		List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		// 实现了 ordered的父类集合
		List<String> orderedPostProcessorNames = new ArrayList<>();
		// 没有实现任何的
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		// 对父类进行分类
		for (String ppName : postProcessorNames) {
			if (processedBeans.contains(ppName)) {
				// skip - already processed in first phase above
			}
			else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				/**
				 * 对实现了PriorityOrdered接口的父类进行实例化
				 */
				priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
			}
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				// 这里仅存储类名
				orderedPostProcessorNames.add(ppName);
			}
			else {
				// 这里仅存储类名
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.
		// 排序
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		// 执行实现了高优先级排序的父类的方法
		invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

		// Next, invoke the BeanFactoryPostProcessors that implement Ordered.
		// 执行实现了排序的父类的方法
		List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String postProcessorName : orderedPostProcessorNames) {
			orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		sortPostProcessors(orderedPostProcessors, beanFactory);
		invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

		// Finally, invoke all other BeanFactoryPostProcessors.
		// 执行实现剩下的父类的方法
		List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String postProcessorName : nonOrderedPostProcessorNames) {
			nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);




		// Clear cached merged bean definitions since the post-processors might have
		// modified the original metadata, e.g. replacing placeholders in values...
		beanFactory.clearMetadataCache();
	}

	public static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {

		// 从bdMap中获取所有BPP
		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

		// Register BeanPostProcessorChecker that logs an info message when
		// a bean is created during BeanPostProcessor instantiation, i.e. when
		// a bean is not eligible for getting processed by all BeanPostProcessors.
		// 期望bean执行的bpp数量 = bdMap中的bpp + 单例池中的bpp
		int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
		// 容器中原先有3个bpp:
		// ApplicationContextAwareProcessor 处理Aware回调接口
		// ApplicationListenerDetector 处理事件相关
		// ImportAwareBeanPostProcessor 为全配置类生成代理对象
		// 此时添加第4个bpp，BeanPostProcessorChecker用来检查容器中的bpp与预期的bpp数据是否一致



		/**
		 *
		 * 在检查bpp放入容器之后，完成所有bpp注册之前，这个期间如果有普通bean被实例化则会被检查bpp判断为没有达到预期
		 */
		beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

		// Separate between BeanPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		// 实现了priorityOrdered接口的bpp对象
		List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
		// 实现了priorityOrdered接口的bpp名字
		List<String> orderedPostProcessorNames = new ArrayList<>();
		// 普通bpp名字
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();

		/**
		 * 实例化高优先级Bpp并注册到容器的bpp集合中
		 * 先实例化，再统一放入到bpps集合中，
		 * 实例化过程中不会受到其他同等优先级bpp回调的影响，因为这个过程中仅仅存在临时集合中，待所有高优先级bpp实例化完成后再统一注册到bpps集合中
		 */
		for (String ppName : postProcessorNames) {
			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				// 实例化高优先级的bpp，如autoBpp、commonBpp
				// 先实例化，再统一放入到bpps集合中
				BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
				priorityOrderedPostProcessors.add(pp);
				if (pp instanceof MergedBeanDefinitionPostProcessor) {
					internalPostProcessors.add(pp);
				}
			}
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				// 不直接实例化的原因是，防止order类型的bpp实例化过程中没有机会执行其他还未实例化的Priority类型的bpp
				orderedPostProcessorNames.add(ppName);
			}
			else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}
		// First, register the BeanPostProcessors that implement PriorityOrdered.
		// 排序
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		// 注册高优先级bpp到beanPostProcessors集合中
		registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

		// Next, register the BeanPostProcessors that implement Ordered.
		/**
		 * 实例化优先级Bpp并注册到容器的bpp集合中
		 */
		// 实例化优先级Bpp
		List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String ppName : orderedPostProcessorNames) {
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			orderedPostProcessors.add(pp);
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		// 排序
		sortPostProcessors(orderedPostProcessors, beanFactory);
		// 注册优先级bpp到beanPostProcessors集合中
		registerBeanPostProcessors(beanFactory, orderedPostProcessors);

		// Now, register all regular BeanPostProcessors.
		/**
		 * 实例化普通Bpp并注册到容器的bpp集合中
		 */
		List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String ppName : nonOrderedPostProcessorNames) {
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			nonOrderedPostProcessors.add(pp);
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

		// Finally, re-register all internal BeanPostProcessors.
		sortPostProcessors(internalPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, internalPostProcessors);

		// Re-register post-processor for detecting inner beans as ApplicationListeners,
		// moving it to the end of the processor chain (for picking up proxies etc).
		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
	}

	private static void sortPostProcessors(List<?> postProcessors, ConfigurableListableBeanFactory beanFactory) {
		// Nothing to sort?
		if (postProcessors.size() <= 1) {
			return;
		}
		Comparator<Object> comparatorToUse = null;
		if (beanFactory instanceof DefaultListableBeanFactory) {
			comparatorToUse = ((DefaultListableBeanFactory) beanFactory).getDependencyComparator();
		}
		if (comparatorToUse == null) {
			comparatorToUse = OrderComparator.INSTANCE;
		}
		postProcessors.sort(comparatorToUse);
	}

	/**
	 * Invoke the given BeanDefinitionRegistryPostProcessor beans.
	 */
	private static void invokeBeanDefinitionRegistryPostProcessors(
			Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

		for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanDefinitionRegistry(registry);
		}
	}

	/**
	 * Invoke the given BeanFactoryPostProcessor beans.
	 */
	private static void invokeBeanFactoryPostProcessors(
			Collection<? extends BeanFactoryPostProcessor> postProcessors, ConfigurableListableBeanFactory beanFactory) {

		for (BeanFactoryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanFactory(beanFactory);
		}
	}

	/**
	 * Register the given BeanPostProcessor beans.
	 */
	private static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanPostProcessor> postProcessors) {

		for (BeanPostProcessor postProcessor : postProcessors) {
			beanFactory.addBeanPostProcessor(postProcessor);
		}
	}


	/**
	 * BeanPostProcessor that logs an info message when a bean is created during
	 * BeanPostProcessor instantiation, i.e. when a bean is not eligible for
	 * getting processed by all BeanPostProcessors.
	 */
	private static final class BeanPostProcessorChecker implements BeanPostProcessor {

		private static final Log logger = LogFactory.getLog(BeanPostProcessorChecker.class);

		private final ConfigurableListableBeanFactory beanFactory;

		private final int beanPostProcessorTargetCount;

		public BeanPostProcessorChecker(ConfigurableListableBeanFactory beanFactory, int beanPostProcessorTargetCount) {
			this.beanFactory = beanFactory;
			this.beanPostProcessorTargetCount = beanPostProcessorTargetCount;
		}

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) {
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) {
			if (!(bean instanceof BeanPostProcessor) && !isInfrastructureBean(beanName) &&
					this.beanFactory.getBeanPostProcessorCount() < this.beanPostProcessorTargetCount) {
				if (logger.isInfoEnabled()) {
					logger.info("Bean '" + beanName + "' of type [" + bean.getClass().getName() +
							"] is not eligible for getting processed by all BeanPostProcessors " +
							"(for example: not eligible for auto-proxying)");
				}
			}
			return bean;
		}

		private boolean isInfrastructureBean(@Nullable String beanName) {
			if (beanName != null && this.beanFactory.containsBeanDefinition(beanName)) {
				BeanDefinition bd = this.beanFactory.getBeanDefinition(beanName);
				return (bd.getRole() == RootBeanDefinition.ROLE_INFRASTRUCTURE);
			}
			return false;
		}
	}

}
