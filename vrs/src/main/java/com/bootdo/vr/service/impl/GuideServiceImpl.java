package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.GuideDao;
import com.bootdo.vr.domain.GuideDO;
import com.bootdo.vr.service.GuideService;



@Service
public class GuideServiceImpl implements GuideService {
	@Autowired
	private GuideDao guideDao;
	
	@Override
	public GuideDO get(Integer id){
		return guideDao.get(id);
	}
	
	@Override
	public List<GuideDO> list(Map<String, Object> map){
		return guideDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return guideDao.count(map);
	}
	
	@Override
	public int save(GuideDO guide){
		return guideDao.save(guide);
	}
	
	@Override
	public int update(GuideDO guide){
		return guideDao.update(guide);
	}
	
	@Override
	public int remove(Integer id){
		return guideDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return guideDao.batchRemove(ids);
	}
	
}
