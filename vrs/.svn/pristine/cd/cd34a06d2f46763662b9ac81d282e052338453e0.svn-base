package com.bootdo.vr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.vr.dao.TopStyleDao;
import com.bootdo.vr.domain.ProductDO;
import com.bootdo.vr.domain.TopStyleDO;
import com.bootdo.vr.service.TopStyleService;



@Service
public class TopStyleServiceImpl implements TopStyleService {
	@Autowired
	private TopStyleDao topStyleDao;
	
	@Override
	public TopStyleDO get(Integer topId){
		return topStyleDao.get(topId);
	}
	
	@Override
	public List<TopStyleDO> list(Map<String, Object> map){
		return topStyleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return topStyleDao.count(map);
	}
	
	@Override
	public int save(TopStyleDO topStyle){
		return topStyleDao.save(topStyle);
	}
	
	@Override
	public int update(TopStyleDO topStyle){
		return topStyleDao.update(topStyle);
	}
	
	@Override
	public int remove(Integer topId){
		return topStyleDao.remove(topId);
	}
	
	@Override
	public int batchRemove(Integer[] topIds){
		return topStyleDao.batchRemove(topIds);
	}

	@Override
	public ProductDO getAllQjt(String activeCode) {
		return topStyleDao.getAllQjt(activeCode);
	}
	
}
