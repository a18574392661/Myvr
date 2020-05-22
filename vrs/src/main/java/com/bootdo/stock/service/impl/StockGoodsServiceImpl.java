package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.StockGoodsDao;
import com.bootdo.stock.domain.StockGoodsDO;
import com.bootdo.stock.service.StockGoodsService;



@Service
public class StockGoodsServiceImpl implements StockGoodsService {
	@Autowired
	private StockGoodsDao stockGoodsDao;
	
	@Override
	public StockGoodsDO get(Integer id){
		return stockGoodsDao.get(id);
	}
	
	@Override
	public List<StockGoodsDO> list(Map<String, Object> map){
		return stockGoodsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return stockGoodsDao.count(map);
	}
	
	@Override
	public int save(StockGoodsDO stockGoods){
		return stockGoodsDao.save(stockGoods);
	}
	
	@Override
	public int update(StockGoodsDO stockGoods){
		return stockGoodsDao.update(stockGoods);
	}
	
	@Override
	public int remove(Integer id){
		return stockGoodsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return stockGoodsDao.batchRemove(ids);
	}

	@Override
	public List<StockGoodsDO> queryByType(Integer typeId) {
		return stockGoodsDao.queryByType(typeId);
	}

	@Override
	public int goodsIn(StockGoodsDO stockGoodsDO) {
		return stockGoodsDao.goodsIn(stockGoodsDO);
	}

	@Override
	public int goodsOut(StockGoodsDO stockGoodsDO) {
		return stockGoodsDao.goodsOut(stockGoodsDO);
	}
	
}
