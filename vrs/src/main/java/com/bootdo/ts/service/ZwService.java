package com.bootdo.ts.service;

import com.bootdo.ts.domain.ZwDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:47:40
 */
public interface ZwService {
	
	ZwDO get(Integer id);
	
	List<ZwDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZwDO zw);
	
	int update(ZwDO zw);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
