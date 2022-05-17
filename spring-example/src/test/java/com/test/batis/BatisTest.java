package com.test.batis;
import com.test.batis.bean.X;
import com.test.batis.config.BatisConfig;
import com.test.batis.dao.AMapper;
import com.test.batis.dao.TMapper;
import com.test.batis.service.TService;
import com.test.batis.mybatis.MySqlSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
@Slf4j(topic = "e")
public class BatisTest {


	/**
	 * 测试整合mybaits
	 */
	@Test
	public void defaultBatis(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(BatisConfig.class);


		TService service = context.getBean(TService.class);

		//Map<String, Object> resultMap = service.queryFroMap(1);

		List<Map<String, Object>> resultList = service.queryFroList();
		//log.debug("resultMap:{}",resultMap);
		log.debug("----------------------------------------------");
		log.debug("resultList:{}",resultList);
	}

	/**
	 * 测试单独试用mybatis
	 */
	@Test
	public void onlyBatis(){
		BatisConfig config  = new BatisConfig();

		DataSource dataSource = config.dataSource();
		TransactionFactory transactionFactory =
				new JdbcTransactionFactory();
		Environment environment =
				new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);


		configuration.addMapper(TMapper.class);


		SqlSessionFactory sqlSessionFactory =
				new SqlSessionFactoryBuilder().build(configuration);

		SqlSession sqlSession = sqlSessionFactory.openSession();

		//对象 本来是一个接口
		//有可能完成了 TMapper接口的实例化
		//动态代理
		TMapper mapper = sqlSession.getMapper(TMapper.class);
		//queryFroMap?
		Map<String, Object> resultMap = mapper.queryFroMap(1);
		log.debug("resultMap:{}",resultMap);
	}




	/**
	 * 测试山寨版的 mybatis
	 */
	@Test
	public void customBatis(){
		MapperFactoryBean a=null;
		TMapper mapper = (TMapper) MySqlSession.getMapper(TMapper.class);
		mapper.queryFroMap(1);
		mapper.queryFroList();

	}

	/**
	 * 测试把一个第三方的对象给spring
	 *
	 * 1、注解（@Service....） X
	 * 2、<bean id="n"></bean> X
	 * 3、注解（@Bean）
	 * 4、factoryBean
	 * 5、spring api
	 * 6、动态想容器注册beandefinition X
	 */
	@Test
	public void customObjectBatis(){
		//一定要mybatis产生？
		//只有他自己知道要干什么事情



		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(BatisConfig.class);

		TService service = context.getBean(TService.class);
		service.queryFroList();
	}

	@Test
	public void beanDefinitionBatis(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext();
		context.scan("com.test.batis.bean");
		context.refresh();

		Object d = context.getBean(X.class);
	}

	/**
	 * 测试ImportBeanDefinitionRegistrar
	 */
	@Test
	public void importBeanDefinitionRegistrarBatis(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(BatisConfig.class);

		TService bean = context.getBean(TService.class);
		bean.queryFroList();
		AMapper aMapper = (AMapper) context.getBean("amapper");
		aMapper.queryFroMap(1);

		TMapper tMapper = (TMapper) context.getBean("tmapper");
		tMapper.queryFroList();
		Object d = context.getBean(X.class);
	}


//	@Test
//	public void test(){
//		TMapper mapper = (TMapper) MySqlSession.getMapper(TMapper.class);
//		mapper.queryFroList();
//	}


}
