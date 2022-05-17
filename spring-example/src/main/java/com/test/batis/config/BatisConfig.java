package com.test.batis.config;

import com.test.batis.dao.TMapper;
import com.test.batis.util.MyImportBeanDefinitionRegistart;
import com.test.batis.mybatis.MySqlSession;
import com.test.batis.util.MyScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.test.batis")
//@MapperScan("com.test.batis.dao")
//@ImportResource("classpath:spring-batis.xml")
@Import(MyImportBeanDefinitionRegistart.class)
//@MyScan
public class BatisConfig {

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource
				= new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setPassword("123456");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/shadow?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
		return driverManagerDataSource;
	}
//
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}


//	@Bean
//	public TMapper tMapper(){
//		TMapper mapper = (TMapper) MySqlSession.getMapper(TMapper.class);
//		return mapper;
//	}
}
