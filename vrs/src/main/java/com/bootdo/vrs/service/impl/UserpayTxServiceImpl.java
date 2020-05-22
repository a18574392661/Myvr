package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.dao.UserpayTxDao;
import com.bootdo.vrs.domain.UserpayTxDO;
import com.bootdo.vrs.service.UserpayTxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class UserpayTxServiceImpl implements UserpayTxService {
	@Autowired
	private UserpayTxDao userpayTxDao;
	
	@Override
	public UserpayTxDO get(Long id){
		return userpayTxDao.get(id);
	}
	
	@Override
	public List<UserpayTxDO> list(Map<String, Object> map){
		return userpayTxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userpayTxDao.count(map);
	}
	
	@Override
	public int save(UserpayTxDO userpayTx){
		return userpayTxDao.save(userpayTx);
	}
	
	@Override
	public int update(UserpayTxDO userpayTx){
		return userpayTxDao.update(userpayTx);
	}
	
	@Override
	public int remove(Long id){
		return userpayTxDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userpayTxDao.batchRemove(ids);
	}
	
}
