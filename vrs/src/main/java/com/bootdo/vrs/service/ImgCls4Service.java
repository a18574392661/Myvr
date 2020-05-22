package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.ImgCls4DO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-14 14:01:20
 */
public interface ImgCls4Service {
	
	ImgCls4DO get(Integer id);
	
	List<ImgCls4DO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImgCls4DO imgCls4);
	
	int update(ImgCls4DO imgCls4);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	ImgCls4DO getCategory(Integer id);

	List<Map<String, String>> contains(Map<String, Object> rootMap);
}
