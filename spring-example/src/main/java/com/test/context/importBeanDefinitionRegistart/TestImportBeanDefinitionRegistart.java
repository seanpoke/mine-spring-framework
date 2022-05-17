package com.test.context.importBeanDefinitionRegistart;
import com.test.context.bfpp.X;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
@Slf4j(topic = "t")
public class TestImportBeanDefinitionRegistart implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
		//log.debug("---TestImportBeanDefinitionRegistart");
		BeanDefinitionBuilder x= BeanDefinitionBuilder.genericBeanDefinition(X.class);
		registry.registerBeanDefinition("x",x.getBeanDefinition());
	}
}
