package com.bootdo.vr.dao;

import com.bootdo.vr.domain.ProductColorsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-07 00:45:37
 */
@Mapper
public interface ProductColorsDao {

	ProductColorsDO get(Integer id);
	
	List<ProductColorsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductColorsDO productColors);
	
	int saveBatch(Map<String, Object> params);
	
	int update(ProductColorsDO productColors);
	
	int remove(Integer id);
	
	int removeByProductId(Integer productId);
	
	int batchRemove(Integer[] ids);
}
