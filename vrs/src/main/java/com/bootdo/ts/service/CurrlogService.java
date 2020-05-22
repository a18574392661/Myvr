package com.bootdo.ts.service;

import com.bootdo.ts.domain.CurrlogDO;

import java.util.List;
import java.util.Map;

/**
 * 添加课程表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:46:13
 */
public interface CurrlogService {
	
	CurrlogDO get(Long id);

	List<Map<String,Object>> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CurrlogDO currlog);
	
	int update(CurrlogDO currlog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);


	List<Map<String,Object>> queryDayRq(List dx,String cid);


	void queryKcCount(String cid);
}
