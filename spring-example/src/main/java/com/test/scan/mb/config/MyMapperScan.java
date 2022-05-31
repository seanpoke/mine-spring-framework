package com.test.scan.mb.config;

import com.test.scan.mb.config.MyMapperScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/30
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyMapperScanRegistrar.class)
public @interface MyMapperScan {

	String value() default "";
}
