package com.test.scan.config;

import com.test.scan.bean.E;
import com.test.scan.bean.X;
import com.test.scan.util.MyBeanNameGenerator;
import com.test.scan.util.MyBeanNameGenerator1;
import com.test.scan.util.anno.TheAnno;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@TheAnno
@ComponentScan(value = "com.test.scan",nameGenerator = MyBeanNameGenerator1.class,
		excludeFilters ={
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = X.class)
},includeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = E.class)
		//@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = E.class)
}

)
//@ComponentScan(value = "com.test.scan.bean")
public class ScanConfig {
}
