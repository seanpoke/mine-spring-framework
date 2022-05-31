package com.test.scan.mb.config;

import com.test.scan.bean.E;
import com.test.scan.bean.X;
import com.test.scan.util.MyBeanNameGenerator1;
import com.test.scan.util.anno.TheAnno;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

//@MapperScan("com.test.scan.mb.mapper")
@MyMapperScan("com.test.scan.mb.mapper")
@ComponentScan(value = "com.test.scan.mb.bean")
public class MyScanConfig {
}
