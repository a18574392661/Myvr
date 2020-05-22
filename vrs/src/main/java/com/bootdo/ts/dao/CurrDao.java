package com.bootdo.ts.dao;

import com.bootdo.ts.domain.CurrDO;

import java.util.List;
import java.util.Map;

import com.bootdo.ts.domain.XjDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 添加课程表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:45:22
 */
@Mapper
public interface CurrDao {

	CurrDO get(Integer id);
	
	List<CurrDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CurrDO curr);
	
	int update(CurrDO curr);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);


	@Select("select count(1) from t_currlog log  where log.cid=#{id}")
	int queryCountKC(@Param("id") String id);

	@Select("select  count(1) from t_curr where cname=#{cname}")
	int queryName(CurrDO currDO);


	@Select("select  count(1) from t_curr where cname=#{cname} and id!=#{id} ")
	int queryNameid(CurrDO currDO);
}
