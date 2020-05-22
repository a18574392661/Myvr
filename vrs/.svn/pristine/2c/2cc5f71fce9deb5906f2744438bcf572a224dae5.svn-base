package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.PayvipDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-25 16:50:07
 */
@Mapper
public interface PayvipDao {

	PayvipDO get(Integer id);
	
	List<PayvipDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PayvipDO payvip);
	
	int update(PayvipDO payvip);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Update("${sql}")
	int editVipStatus(@Param("sql") String sql);
}
