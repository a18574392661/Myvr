package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.dao.HxLogDao;
import com.bootdo.vrs.domain.HxLogDO;
import com.bootdo.vrs.service.HxLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class HxLogServiceImpl implements HxLogService {
	@Autowired
	private HxLogDao hxLogDao;
	
	@Override
	public HxLogDO get(Long id){
		return hxLogDao.get(id);
	}
	
	@Override
	public List<HxLogDO> list(Map<String, Object> map){
		return hxLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return hxLogDao.count(map);
	}
	
	@Override
	public int save(HxLogDO hxLog){
		return hxLogDao.save(hxLog);
	}
	
	@Override
	public int update(HxLogDO hxLog){
		return hxLogDao.update(hxLog);
	}
	
	@Override
	public int remove(Long id){
		return hxLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return hxLogDao.batchRemove(ids);
	}
	
}
