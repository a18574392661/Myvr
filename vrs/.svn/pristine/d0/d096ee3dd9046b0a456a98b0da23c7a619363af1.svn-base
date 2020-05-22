package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.PaydetailesDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 支付介绍详情价格表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 10:19:09
 */
@Mapper
public interface PaydetailesDao {

	PaydetailesDO get(Integer id);
	
	List<PaydetailesDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PaydetailesDO paydetailes);
	
	int update(PaydetailesDO paydetailes);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
