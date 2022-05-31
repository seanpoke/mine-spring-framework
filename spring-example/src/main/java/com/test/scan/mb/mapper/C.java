package com.test.scan.mb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author : shaonan.xiao
 * @date : 2022/5/30
 **/
@Mapper
public interface C {

	@Select("select * from xxx")
	public void doSql();
}
