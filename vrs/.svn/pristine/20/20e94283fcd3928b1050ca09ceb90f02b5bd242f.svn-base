package com.bootdo.vr.dao;

import com.bootdo.vr.domain.ProductLeftStyleImgsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产中选中的热点图表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 20:10:53
 */
@Mapper
public interface ProductLeftStyleImgsDao {

	List<Map<String, Object>> queryForLeftRdtTree(Integer[] ids);
	
	ProductLeftStyleImgsDO get(Integer id);
	
	List<ProductLeftStyleImgsDO> list(Map<String,Object> map);
	
	int saveBatch(Map<String,Object> map);
	
	int removeByProductId(Integer productId);
	
	int count(Map<String,Object> map);
	
	int save(ProductLeftStyleImgsDO productLeftStyleImgs);
	
	int update(ProductLeftStyleImgsDO productLeftStyleImgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
