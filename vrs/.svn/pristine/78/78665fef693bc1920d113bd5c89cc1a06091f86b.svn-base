package com.bootdo.vr.dao;

import com.bootdo.vr.domain.ProductDO;
import com.bootdo.vr.domain.TopStyleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:20
 */
@Mapper
public interface TopStyleDao {

	TopStyleDO get(Integer topId);
	
	ProductDO getAllQjt(String activeCode);
	
	List<TopStyleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TopStyleDO topStyle);
	
	int update(TopStyleDO topStyle);
	
	int remove(Integer top_id);
	
	int batchRemove(Integer[] topIds);
}
