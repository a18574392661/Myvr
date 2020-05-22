package com.bootdo.ts.dao;

import com.bootdo.ts.domain.CurrlogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 添加课程表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:46:13
 */
@Mapper
public interface CurrlogDao {

	CurrlogDO get(Long id);
	
	List<CurrlogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CurrlogDO currlog);
	
	int update(CurrlogDO currlog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);


	int queryDayJkCount(CurrlogDO currlogDO);


	CurrlogDO getCurrCnames(String id);

	List<Map<String,Object>> list2(Map<String,Object> map);

	@Select("select  * from t_currlog where dayDate=#{rq} and ji=#{id}")
	CurrlogDO queryRqAndJid(@Param("id") String id,@Param("rq") String rq);


	//查询可以预约的1日期
	public  List<CurrlogDO> currAllLogs();


	public  List<CurrlogDO> currAllLogsKc(@Param("dates")String dates);

	int total(Map<String,Object> map);


	 CurrlogDO querySJDays(@Param("days") String days,@Param("dates") String dates,@Param("cid")String cid);


	@Select("\n" +
			"SELECT count(1) from t_zw WHERE cid =(\n" +
			"SELECT cid FROM t_zw z\n" +
			"WHERE id=#{id})")
	int queryCountzw(@Param("id") Integer id);


	//一节课 有多个课程 傻逼设计的
	@Select("\n" +
			"SELECT c.*,k.cname as 'cname' FROM t_currlog  c \n" +
			"left join t_curr k\n" +
			"on c.cid=k.id\n" +
			"\n" +
			"WHERE c.dayDate=#{dates} and c.ji=\n" +
			"#{jie} ")
	List<CurrlogDO> getDayJiCnames(@Param("dates")String dates,@Param("jie") String jie);


}
