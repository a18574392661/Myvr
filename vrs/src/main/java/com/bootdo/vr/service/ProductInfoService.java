package com.bootdo.vr.service;

import com.bootdo.vr.domain.ProductInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 产品套餐基本信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-28 15:59:55
 */
public interface ProductInfoService {
	
	ProductInfoDO get(Integer id);
	
	List<ProductInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductInfoDO productInfo);
	
	int update(ProductInfoDO productInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
