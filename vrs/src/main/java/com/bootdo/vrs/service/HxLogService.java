package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.HxLogDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-24 21:35:02
 */
public interface HxLogService {
	
	HxLogDO get(Long id);
	
	List<HxLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HxLogDO hxLog);
	
	int update(HxLogDO hxLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
