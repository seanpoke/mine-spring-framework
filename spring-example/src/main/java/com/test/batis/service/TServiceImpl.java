package com.test.batis.service;

import com.test.batis.dao.AMapper;
import com.test.batis.dao.TMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
@Service
public class TServiceImpl implements TService{
	//1、对象？
	//2、tMapper如何产生的？谁产生的
	@Autowired
	TMapper tMapper;
	@Autowired
	AMapper aMapper;

	public void settMapper(TMapper tMapper) {
		this.tMapper = tMapper;
	}

	public void setaMapper(AMapper aMapper) {
		this.aMapper = aMapper;
	}

	@Override
	public List<Map<String, Object>> queryFroList() {
		//aMapper.queryFroList();
		return tMapper.queryFroList();
	}


	@Override
	public Map<String, Object> queryFroMap(Integer id) {
		return tMapper.queryFroMap(id);
	}
}
