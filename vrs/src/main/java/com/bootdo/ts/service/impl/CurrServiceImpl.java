package com.bootdo.ts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.ts.dao.CurrDao;
import com.bootdo.ts.domain.CurrDO;
import com.bootdo.ts.service.CurrService;



@Service
public class CurrServiceImpl implements CurrService {
	@Autowired
	private CurrDao currDao;
	
	@Override
	public CurrDO get(Integer id){
		return currDao.get(id);
	}
	
	@Override
	public List<CurrDO> list(Map<String, Object> map){
		return currDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return currDao.count(map);
	}
	
	@Override
	public int save(CurrDO curr){
		return currDao.save(curr);
	}
	
	@Override
	public int update(CurrDO curr){
		return currDao.update(curr);
	}
	
	@Override
	public int remove(Integer id){
		return currDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return currDao.batchRemove(ids);
	}
	
}
