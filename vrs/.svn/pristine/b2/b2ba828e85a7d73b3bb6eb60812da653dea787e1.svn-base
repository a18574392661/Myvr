package com.bootdo.ts.dao;

import com.bootdo.ts.domain.XjDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 选择第几节课
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 14:13:54
 */
@Mapper
public interface XjDao {

	XjDO get(Integer id);
	
	List<XjDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(XjDO xj);
	
	int update(XjDO xj);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select count(1) from t_currlog log  where log.ji=#{id}")
	int queryCountKC(@Param("id") String id);

	@Select("select  count(1) from t_xj where name=#{name}")
	int queryName(XjDO xjDO);


	@Select("select  count(1) from t_xj where name=#{name} and id!=#{id} ")
	int queryNameid(XjDO xjDO);


}
