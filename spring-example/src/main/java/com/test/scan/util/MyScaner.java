package com.test.scan.util;

import com.test.autoModel.inject.G;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j(topic = "e")
public class MyScaner {
	File f = new File(this.getClass().getResource("/").getPath());
	public List<String> listName = new ArrayList<>();
	public Map<String, AbstractBeanDefinition> map = new HashMap<>();


	//模拟spring完成了扫描符合规则（spring的默认）
	//这个方法一旦调用 listName存了所有符合规则的bean的名字
	//这个方法一旦调用map 存了所有符合规则的bean的名字和与之对应的bd
	public void scan(String packageName) throws ClassNotFoundException {
		//包名对应的路径 com.xxx =com\\xx
		String scanPath="";
		//类的存放路径
		//D:\work\spring-Framework-5.2.x\spring-example\out\production
		String rootpath = f.getPath();
		//scanPath = com\test\scan\bean
		scanPath = packageName.replaceAll("\\.","\\\\");
		rootpath=rootpath+"\\"+scanPath;
		File rootdir = new File(rootpath);
		String[] list = rootdir.list();
		for (String s : list) {
			//s =F
			s=s.replaceAll(".class","");
			//beanName =f
			String beanName = s.toLowerCase();
			//com.test.scan.bean.F
			s=packageName+"."+s;
			//反射加载类 F.class
			Class<?> clazz = Class.forName(s);
			//有没有加Component
			if (clazz.isAnnotationPresent(Component.class)) {
				GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
				beanDefinition.setBeanClass(clazz);
				if (clazz.isAnnotationPresent(Scope.class))
					beanDefinition.setScope(clazz.getAnnotation(Scope.class).value());

				map.put(beanName, beanDefinition);
				listName.add(beanName);
			}
		}
	}

	public List<String> getListName() {
		return listName;
	}

	public Map<String, AbstractBeanDefinition> getMap() {
		return map;
	}

	public static void main(String[] args) throws ClassNotFoundException {

	}
}
