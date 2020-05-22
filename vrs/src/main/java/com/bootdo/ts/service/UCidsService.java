package com.bootdo.ts.service;

import com.bootdo.ts.domain.UCidsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:47:17
 */
public interface UCidsService {
	
	UCidsDO get(Integer id);
	
	List<UCidsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UCidsDO uCids);
	
	int update(UCidsDO uCids);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
