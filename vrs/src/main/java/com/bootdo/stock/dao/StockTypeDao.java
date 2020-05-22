package com.bootdo.stock.dao;

import com.bootdo.stock.domain.StockTypeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 库存管理 - 商品分类表
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
@Mapper
public interface StockTypeDao {

	StockTypeDO get(Integer id);
	
	List<StockTypeDO> queryByPid(Integer pid);
	
	List<StockTypeDO> queryFirstLevel();//查找所有的一级菜单
	
	List<StockTypeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StockTypeDO stockType);
	
	int update(StockTypeDO stockType);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
