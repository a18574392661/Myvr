package com.bootdo.vr.dao;

import com.bootdo.vr.domain.ProductImgsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 17:21:44
 */
@Mapper
public interface ProductImgsDao {

	List<Map<String, Object>> queryForRdtTree(Integer[] ids);
	
	ProductImgsDO get(Integer id);
	
	List<ProductImgsDO> list(Map<String,Object> map);
	
	int saveBatch(Map<String,Object> map);
	
	int removeByProductId(Integer productId);
	
	int count(Map<String,Object> map);
	
	int save(ProductImgsDO productImgs);
	
	int update(ProductImgsDO productImgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
