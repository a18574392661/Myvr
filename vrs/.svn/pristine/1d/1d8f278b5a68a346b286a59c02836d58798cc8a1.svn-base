package com.bootdo.ts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.ts.dao.XjDao;
import com.bootdo.ts.domain.XjDO;
import com.bootdo.ts.service.XjService;



@Service
public class XjServiceImpl implements XjService {
	@Autowired
	private XjDao xjDao;
	
	@Override
	public XjDO get(Integer id){
		return xjDao.get(id);
	}
	
	@Override
	public List<XjDO> list(Map<String, Object> map){
		return xjDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xjDao.count(map);
	}
	
	@Override
	public int save(XjDO xj){
		return xjDao.save(xj);
	}
	
	@Override
	public int update(XjDO xj){
		return xjDao.update(xj);
	}
	
	@Override
	public int remove(Integer id){
		return xjDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return xjDao.batchRemove(ids);
	}
	
}
