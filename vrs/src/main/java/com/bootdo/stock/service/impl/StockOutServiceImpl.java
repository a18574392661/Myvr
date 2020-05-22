package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.StockOutDao;
import com.bootdo.stock.domain.StockOutDO;
import com.bootdo.stock.service.StockOutService;



@Service
public class StockOutServiceImpl implements StockOutService {
	@Autowired
	private StockOutDao stockOutDao;
	
	@Override
	public StockOutDO get(Integer id){
		return stockOutDao.get(id);
	}
	
	@Override
	public List<StockOutDO> list(Map<String, Object> map){
		return stockOutDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return stockOutDao.count(map);
	}
	
	@Override
	public int save(StockOutDO stockOut){
		return stockOutDao.save(stockOut);
	}
	
	@Override
	public int update(StockOutDO stockOut){
		return stockOutDao.update(stockOut);
	}
	
	@Override
	public int remove(Integer id){
		return stockOutDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return stockOutDao.batchRemove(ids);
	}
	
}
