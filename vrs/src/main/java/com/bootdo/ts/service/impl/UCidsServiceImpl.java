package com.bootdo.ts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.ts.dao.UCidsDao;
import com.bootdo.ts.domain.UCidsDO;
import com.bootdo.ts.service.UCidsService;



@Service
public class UCidsServiceImpl implements UCidsService {
	@Autowired
	private UCidsDao uCidsDao;
	
	@Override
	public UCidsDO get(Integer id){
		return uCidsDao.get(id);
	}
	
	@Override
	public List<UCidsDO> list(Map<String, Object> map){
		return uCidsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return uCidsDao.count(map);
	}
	
	@Override
	public int save(UCidsDO uCids){
		return uCidsDao.save(uCids);
	}
	
	@Override
	public int update(UCidsDO uCids){
		return uCidsDao.update(uCids);
	}
	
	@Override
	public int remove(Integer id){
		return uCidsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return uCidsDao.batchRemove(ids);
	}
	
}
