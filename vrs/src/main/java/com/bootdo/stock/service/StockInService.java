package com.bootdo.stock.service;

import com.bootdo.stock.domain.StockInDO;

import java.util.List;
import java.util.Map;

/**
 * 库存管理 - 商品入库表
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
public interface StockInService {
	
	StockInDO get(Integer id);
	
	List<StockInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StockInDO stockIn);
	
	int update(StockInDO stockIn);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
