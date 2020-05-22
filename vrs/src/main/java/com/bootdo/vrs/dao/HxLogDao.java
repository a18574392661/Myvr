package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.HxLogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-24 21:35:02
 */
@Mapper
public interface HxLogDao {

	HxLogDO get(Long id);
	
	List<HxLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(HxLogDO hxLog);
	
	int update(HxLogDO hxLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	@Select("select sum(price) from  vrs_hx_log where status=1  and pid=#{uid}")
	BigDecimal getUserPrice(@Param("uid") Long uid);

	@Select("select  count(1) from vrs_hx_log where ordercode=#{ordercode}")
	int queryHxOrderCode(@Param("ordercode") String code);
}
