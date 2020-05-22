package com.bootdo.stock.service;

import com.bootdo.stock.domain.StockOutDO;

import java.util.List;
import java.util.Map;

/**
 * 库存管理 - 商品出库(卖出)表
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
public interface StockOutService {
	
	StockOutDO get(Integer id);
	
	List<StockOutDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StockOutDO stockOut);
	
	int update(StockOutDO stockOut);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
