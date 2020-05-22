package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ImgCls2DO;
import com.bootdo.vrs.domain.ImgCls3DO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 第二个分类表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-14 11:13:55
 */
@Mapper
public interface ImgCls3Dao {

	ImgCls3DO get(Integer id);
	
	List<ImgCls3DO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ImgCls3DO imgCls3);
	
	int update(ImgCls3DO imgCls3);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	ImgCls3DO getCategory(Integer id);

	List<Map<String, String>> contains(Map<String, Object> rootMap);
}
