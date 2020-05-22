package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.dao.PayvipDao;
import com.bootdo.vrs.domain.PayvipDO;
import com.bootdo.vrs.service.PayvipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class PayvipServiceImpl implements PayvipService {
	@Autowired
	private PayvipDao payvipDao;
	
	@Override
	public PayvipDO get(Integer id){
		return payvipDao.get(id);
	}
	
	@Override
	public List<PayvipDO> list(Map<String, Object> map){
		return payvipDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return payvipDao.count(map);
	}
	
	@Override
	public int save(PayvipDO payvip){
		return payvipDao.save(payvip);
	}
	
	@Override
	public int update(PayvipDO payvip){
		return payvipDao.update(payvip);
	}
	
	@Override
	public int remove(Integer id){
		return payvipDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return payvipDao.batchRemove(ids);
	}
	
}
