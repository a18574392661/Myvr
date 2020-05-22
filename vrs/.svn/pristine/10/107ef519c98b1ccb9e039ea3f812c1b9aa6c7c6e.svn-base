package com.bootdo.vr.dao;

import com.bootdo.vr.domain.LeftStyleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:19
 */
@Mapper
public interface LeftStyleDao {

	List<Map<String, Object>> queryAllTopAndLeftStyles();
	
	LeftStyleDO get(Integer leftId);
	
	List<LeftStyleDO> queryByTopStyleId(Integer topStyleId);
	
	List<LeftStyleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LeftStyleDO leftStyle);
	
	int update(LeftStyleDO leftStyle);
	
	int remove(Integer left_id);
	
	int batchRemove(Integer[] leftIds);
}
