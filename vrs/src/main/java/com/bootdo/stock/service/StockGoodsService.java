package com.bootdo.stock.service;

import com.bootdo.stock.domain.StockGoodsDO;

import java.util.List;
import java.util.Map;

/**
 * 库存管理 - 商品库
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
public interface StockGoodsService {
	
	StockGoodsDO get(Integer id);
	
	List<StockGoodsDO> queryByType(Integer typeId);
	
	List<StockGoodsDO> list(Map<String, Object> map);
	
	int goodsIn(StockGoodsDO stockGoodsDO);
	
	int goodsOut(StockGoodsDO stockGoodsDO);
	
	int count(Map<String, Object> map);
	
	int save(StockGoodsDO stockGoods);
	
	int update(StockGoodsDO stockGoods);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
