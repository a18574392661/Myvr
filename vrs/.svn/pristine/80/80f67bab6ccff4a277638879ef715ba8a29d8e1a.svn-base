package com.bootdo.vr.service;

import com.bootdo.vr.domain.ProductActiveCodeDO;

import java.util.List;
import java.util.Map;

/**
 * 产品套餐激活码表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-28 19:05:06
 */
public interface ProductActiveCodeService {
	
	ProductActiveCodeDO getProductActiveCodeByCode(String productActiveCode);
	
	ProductActiveCodeDO get(Integer id);
	
	List<ProductActiveCodeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductActiveCodeDO productActiveCode);
	
	int saveBatch(Map<String, Object> params);
	
	int update(ProductActiveCodeDO productActiveCode);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
