package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.dao.UserHxDao;
import com.bootdo.vrs.domain.UserHxDO;
import com.bootdo.vrs.service.UserHxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class UserHxServiceImpl implements UserHxService {
	@Autowired
	private UserHxDao userHxDao;
	
	@Override
	public UserHxDO get(Integer id){
		return userHxDao.get(id);
	}
	
	@Override
	public List<UserHxDO> list(Map<String, Object> map){
		return userHxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userHxDao.count(map);
	}
	
	@Override
	public int save(UserHxDO userHx){
		return userHxDao.save(userHx);
	}
	
	@Override
	public int update(UserHxDO userHx){
		return userHxDao.update(userHx);
	}
	
	@Override
	public int remove(Integer id){
		return userHxDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userHxDao.batchRemove(ids);
	}
	
}
