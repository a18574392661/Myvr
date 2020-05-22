package com.bootdo.vr.service;

import com.bootdo.vr.domain.RomeTypeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:20
 */
public interface RomeTypeService {
	
	RomeTypeDO get(Integer romeId);
	
	List<RomeTypeDO> queryByLeftStyleId(Integer leftStyleId);
	
	List<RomeTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RomeTypeDO romeType);
	
	int update(RomeTypeDO romeType);
	
	int remove(Integer romeId);
	
	int batchRemove(Integer[] romeIds);
}
