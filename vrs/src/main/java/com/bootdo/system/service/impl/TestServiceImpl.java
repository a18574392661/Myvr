package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.TestDao;
import com.bootdo.system.domain.TestDO;
import com.bootdo.system.service.TestService;



@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;
	
	@Override
	public TestDO get(Long id){
		return testDao.get(id);
	}
	
	@Override
	public List<TestDO> list(Map<String, Object> map){
		return testDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return testDao.count(map);
	}
	
	@Override
	public int save(TestDO test){
		return testDao.save(test);
	}
	
	@Override
	public int update(TestDO test){
		return testDao.update(test);
	}
	
	@Override
	public int remove(Long id){
		return testDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return testDao.batchRemove(ids);
	}
	
}
