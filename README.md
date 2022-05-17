# <img src="src/docs/asciidoc/images/spring-framework.png" width="80" height="80"> Spring Framework [![Build Status](https://ci.spring.io/api/v1/teams/spring-framework/pipelines/spring-framework-5.2.x/jobs/build/badge)](https://ci.spring.io/teams/spring-framework/pipelines/spring-framework-5.2.x?groups=Build")

自己学习Spring源码笔记
[跳转到官网](https://spring.io/projects). 


## Spring日志框架
JCL、Slf4j、Log4j、Log4j2

## 各类高级应用
@Autowire、@Resource、@Order、@Lookup、@DependsOn、Supplier

## Mybatis引出的思考
- 注册对象和注册类的区别
- BeanDefinition工作原理
- 第三方的mapper对象如何如何放入容器中
- FactoryBean和BeanFactory的区别
- Mybatis注入mapper对象的方式
- 新老版本Mybatis集成Spring基本原理

## Spring容器
- 如何让BeanFactory实现自动注入的功能
- BeanName的生成策略
- AnnotationConfigApplicationContext
  - BeanFactoryPostProcessor的功能