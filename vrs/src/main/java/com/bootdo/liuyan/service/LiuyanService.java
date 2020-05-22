package com.bootdo.liuyan.service;

import com.bootdo.liuyan.domain.LiuyanDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-03 11:15:18
 */
public interface LiuyanService {
	
	LiuyanDO get(Long id);
	
	List<LiuyanDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LiuyanDO liuyan);
	
	int update(LiuyanDO liuyan);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
