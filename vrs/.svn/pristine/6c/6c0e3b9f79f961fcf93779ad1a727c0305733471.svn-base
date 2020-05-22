package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.StockTypeDao;
import com.bootdo.stock.domain.StockTypeDO;
import com.bootdo.stock.service.StockTypeService;



@Service
public class StockTypeServiceImpl implements StockTypeService {
	@Autowired
	private StockTypeDao stockTypeDao;
	
	@Override
	public StockTypeDO get(Integer id){
		return stockTypeDao.get(id);
	}
	
	@Override
	public List<StockTypeDO> queryByPid(Integer pid){
		return stockTypeDao.queryByPid(pid);
	}
	
	@Override
	public List<StockTypeDO> list(Map<String, Object> map){
		return stockTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return stockTypeDao.count(map);
	}
	
	@Override
	public int save(StockTypeDO stockType){
		return stockTypeDao.save(stockType);
	}
	
	@Override
	public int update(StockTypeDO stockType){
		return stockTypeDao.update(stockType);
	}
	
	@Override
	public int remove(Integer id){
		return stockTypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return stockTypeDao.batchRemove(ids);
	}

	@Override
	public List<StockTypeDO> queryFirstLevel() {
		return stockTypeDao.queryFirstLevel();
	}
	
}
