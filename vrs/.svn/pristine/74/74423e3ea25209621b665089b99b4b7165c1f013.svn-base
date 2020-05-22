package com.bootdo.vr.service;

import com.bootdo.vr.domain.LeftStyleDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:19
 */
public interface LeftStyleService {
	
	List<Map<String, Object>> queryAllTopAndLeftStyles();
	
	LeftStyleDO get(Integer leftId);
	
	List<LeftStyleDO> queryByTopStyleId(Integer topStyleId);
	
	List<LeftStyleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeftStyleDO leftStyle);
	
	int update(LeftStyleDO leftStyle);
	
	int remove(Integer leftId);
	
	int batchRemove(Integer[] leftIds);
}
