package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.ImgCls2DO;
import com.bootdo.vrs.domain.ImgClsDO;

import java.util.List;
import java.util.Map;

/**
 * 第二个分类表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-14 09:53:32
 */
public interface ImgCls2Service {
	
	ImgCls2DO get(Integer id);
	
	List<ImgCls2DO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImgCls2DO imgCls2);
	
	int update(ImgCls2DO imgCls2);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	ImgCls2DO getCategory(Integer id);
	
	List<Map<String,Object>> queryTableCls(String tableName);
	List<Map<String,String>> getMenu();
	
	List<Map<String,String>> contains(Map<String, Object> rootMap);

	List<ImgCls2DO> listTopThree(Map<String, Object> params);

	List<ImgClsDO> imgClsPars(String pid);
	
}

