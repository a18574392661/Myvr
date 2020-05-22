package com.bootdo.ts.dao;

import com.bootdo.ts.domain.UCidsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:47:17
 */
@Mapper
public interface UCidsDao {

	UCidsDO get(Integer id);
	
	List<UCidsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UCidsDO uCids);
	
	int update(UCidsDO uCids);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	//当前节课 这个座位是否有人预约
	@Select("select count(1) from t_u_cids where cid=#{cid} ")
	int queryKzw(@Param("cid")String cid);

	//当前课程是否被预约
	@Select("select  count(1) from t_u_cids where  cid=#{cid}")
	int queryKcCount(@Param("cid")String cid);
}
