package com.bootdo.stock.dao;

import com.bootdo.stock.domain.StockInDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 库存管理 - 商品入库表
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
@Mapper
public interface StockInDao {

	StockInDO get(Integer id);
	
	List<StockInDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StockInDO stockIn);
	
	int update(StockInDO stockIn);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
