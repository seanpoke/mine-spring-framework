package com.test.lifeCycle.config;

import com.test.lifeCycle.util.EnableAspectAop;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//@EnableAspectAop
@EnableAspectJAutoProxy
@ComponentScan("com.test.lifeCycle.factory")
public class LifeCycleConfig {
}
