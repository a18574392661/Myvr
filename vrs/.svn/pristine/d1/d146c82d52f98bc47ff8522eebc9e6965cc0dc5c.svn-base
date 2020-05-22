package com.bootdo.vr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.vr.domain.ColorTypeDO;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:19
 */
@Mapper
public interface ColorTypeDao {

	List<Map<String, Object>> queryForProductTree(Integer[] ids);
	
	ColorTypeDO get(Integer id);
	
	List<ColorTypeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ColorTypeDO colorType);
	
	int update(ColorTypeDO colorType);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
