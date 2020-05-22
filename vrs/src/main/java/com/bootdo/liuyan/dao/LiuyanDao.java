package com.bootdo.liuyan.dao;

import com.bootdo.liuyan.domain.LiuyanDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-03 11:15:18
 */
@Mapper
public interface LiuyanDao {

	LiuyanDO get(Long id);
	
	List<LiuyanDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LiuyanDO liuyan);
	
	int update(LiuyanDO liuyan);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
