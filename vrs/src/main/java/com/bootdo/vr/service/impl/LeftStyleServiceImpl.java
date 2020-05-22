package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.LeftStyleDao;
import com.bootdo.vr.domain.LeftStyleDO;
import com.bootdo.vr.service.LeftStyleService;



@Service
public class LeftStyleServiceImpl implements LeftStyleService {
	@Autowired
	private LeftStyleDao leftStyleDao;
	
	@Override
	public List<Map<String, Object>> queryAllTopAndLeftStyles(){
		return leftStyleDao.queryAllTopAndLeftStyles();
	}
	
	@Override
	public LeftStyleDO get(Integer leftId){
		return leftStyleDao.get(leftId);
	}
	
	@Override
	public List<LeftStyleDO> list(Map<String, Object> map){
		return leftStyleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return leftStyleDao.count(map);
	}
	
	@Override
	public int save(LeftStyleDO leftStyle){
		return leftStyleDao.save(leftStyle);
	}
	
	@Override
	public int update(LeftStyleDO leftStyle){
		return leftStyleDao.update(leftStyle);
	}
	
	@Override
	public int remove(Integer leftId){
		return leftStyleDao.remove(leftId);
	}
	
	@Override
	public int batchRemove(Integer[] leftIds){
		return leftStyleDao.batchRemove(leftIds);
	}

	@Override
	public List<LeftStyleDO> queryByTopStyleId(Integer topStyleId) {
		return leftStyleDao.queryByTopStyleId(topStyleId);
	}
	
}
