package com.test.lifeCycle.util;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(JDKProxyBeanPostProcessor.class)
public @interface EnableAspectAop {
}
