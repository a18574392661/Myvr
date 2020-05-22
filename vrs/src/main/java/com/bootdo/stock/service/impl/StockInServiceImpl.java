package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.StockInDao;
import com.bootdo.stock.domain.StockInDO;
import com.bootdo.stock.service.StockInService;



@Service
public class StockInServiceImpl implements StockInService {
	@Autowired
	private StockInDao stockInDao;
	
	@Override
	public StockInDO get(Integer id){
		return stockInDao.get(id);
	}
	
	@Override
	public List<StockInDO> list(Map<String, Object> map){
		return stockInDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return stockInDao.count(map);
	}
	
	@Override
	public int save(StockInDO stockIn){
		return stockInDao.save(stockIn);
	}
	
	@Override
	public int update(StockInDO stockIn){
		return stockInDao.update(stockIn);
	}
	
	@Override
	public int remove(Integer id){
		return stockInDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return stockInDao.batchRemove(ids);
	}
	
}
