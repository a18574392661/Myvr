package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.RomeTypeDao;
import com.bootdo.vr.domain.RomeTypeDO;
import com.bootdo.vr.service.RomeTypeService;



@Service
public class RomeTypeServiceImpl implements RomeTypeService {
	@Autowired
	private RomeTypeDao romeTypeDao;
	
	@Override
	public RomeTypeDO get(Integer romeId){
		return romeTypeDao.get(romeId);
	}
	
	@Override
	public List<RomeTypeDO> list(Map<String, Object> map){
		return romeTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return romeTypeDao.count(map);
	}
	
	@Override
	public int save(RomeTypeDO romeType){
		return romeTypeDao.save(romeType);
	}
	
	@Override
	public int update(RomeTypeDO romeType){
		return romeTypeDao.update(romeType);
	}
	
	@Override
	public int remove(Integer romeId){
		return romeTypeDao.remove(romeId);
	}
	
	@Override
	public int batchRemove(Integer[] romeIds){
		return romeTypeDao.batchRemove(romeIds);
	}

	@Override
	public List<RomeTypeDO> queryByLeftStyleId(Integer leftStyleId) {
		return romeTypeDao.queryByLeftStyleId(leftStyleId);
	}
	
}
