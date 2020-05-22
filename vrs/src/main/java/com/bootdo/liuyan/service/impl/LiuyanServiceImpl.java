package com.bootdo.liuyan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.liuyan.dao.LiuyanDao;
import com.bootdo.liuyan.domain.LiuyanDO;
import com.bootdo.liuyan.service.LiuyanService;



@Service
public class LiuyanServiceImpl implements LiuyanService {
	@Autowired
	private LiuyanDao liuyanDao;
	
	@Override
	public LiuyanDO get(Long id){
		return liuyanDao.get(id);
	}
	
	@Override
	public List<LiuyanDO> list(Map<String, Object> map){
		return liuyanDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return liuyanDao.count(map);
	}
	
	@Override
	public int save(LiuyanDO liuyan){
		return liuyanDao.save(liuyan);
	}
	
	@Override
	public int update(LiuyanDO liuyan){
		return liuyanDao.update(liuyan);
	}
	
	@Override
	public int remove(Long id){
		return liuyanDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return liuyanDao.batchRemove(ids);
	}
	
}
