package com.bootdo.vr.service;

import com.bootdo.vr.domain.ColorTypeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:19
 */
public interface ColorTypeService {
	
	List<Map<String, Object>> queryForProductTree(Integer[] ids);
	
	ColorTypeDO get(Integer id);
	
	List<ColorTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ColorTypeDO colorType);
	
	int update(ColorTypeDO colorType);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
