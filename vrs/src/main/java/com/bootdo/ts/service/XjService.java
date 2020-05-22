package com.bootdo.ts.service;

import com.bootdo.ts.domain.XjDO;

import java.util.List;
import java.util.Map;

/**
 * 选择第几节课
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 14:13:54
 */
public interface XjService {
	
	XjDO get(Integer id);
	
	List<XjDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XjDO xj);
	
	int update(XjDO xj);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
