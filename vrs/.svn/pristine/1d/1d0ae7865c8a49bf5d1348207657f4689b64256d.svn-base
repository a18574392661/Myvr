package com.bootdo.vr.service;

import com.bootdo.vr.domain.ProductLeftStyleDO;

import java.util.List;
import java.util.Map;

/**
 * 产品套餐中的小风格全景分类图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-28 15:59:55
 */
public interface ProductLeftStyleService {
	
	ProductLeftStyleDO get(Integer id);
	
	List<ProductLeftStyleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductLeftStyleDO productLeftStyle);
	
	int saveBatch(Map<String, Object> params);
	
	int update(ProductLeftStyleDO productLeftStyle);
	
	int removeByProductId(Integer productId);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
