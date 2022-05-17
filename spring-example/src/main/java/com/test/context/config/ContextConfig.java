package com.test.context.config;

import com.test.context.importBeanDefinitionRegistart.TestImportBeanDefinitionRegistart;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

//@ComponentScan("com.test.context")
@ComponentScan("com.test.context.bfpp")
//@Import(TestImportBeanDefinitionRegistart.class)
public class ContextConfig {
}
