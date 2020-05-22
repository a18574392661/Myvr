package com.bootdo.ts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.ts.dao.ZwDao;
import com.bootdo.ts.domain.ZwDO;
import com.bootdo.ts.service.ZwService;



@Service
public class ZwServiceImpl implements ZwService {
	@Autowired
	private ZwDao zwDao;
	
	@Override
	public ZwDO get(Integer id){
		return zwDao.get(id);
	}
	
	@Override
	public List<ZwDO> list(Map<String, Object> map){
		return zwDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zwDao.count(map);
	}
	
	@Override
	public int save(ZwDO zw){
		return zwDao.save(zw);
	}
	
	@Override
	public int update(ZwDO zw){
		return zwDao.update(zw);
	}
	
	@Override
	public int remove(Integer id){
		return zwDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return zwDao.batchRemove(ids);
	}
	
}
