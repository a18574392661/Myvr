package com.bootdo.school.service;


import com.bootdo.school.domain.BusinessDO;

import java.util.List;
import java.util.Map;

/**
 * 商家表 封面图 经度纬度 富文本详细信息 等
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-07 20:19:47
 */
public interface BusinessService {
	
	BusinessDO get(Integer id);
	
	List<BusinessDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BusinessDO business);
	
	int update(BusinessDO business);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	BusinessDO queryCouponId(String uid);
}
