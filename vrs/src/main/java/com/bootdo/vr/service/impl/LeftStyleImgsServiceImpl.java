package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.LeftStyleImgsDao;
import com.bootdo.vr.domain.LeftStyleImgsDO;
import com.bootdo.vr.service.LeftStyleImgsService;



@Service
public class LeftStyleImgsServiceImpl implements LeftStyleImgsService {
	@Autowired
	private LeftStyleImgsDao leftStyleImgsDao;
	
	@Override
	public LeftStyleImgsDO get(Integer id){
		return leftStyleImgsDao.get(id);
	}
	
	@Override
	public List<LeftStyleImgsDO> list(Map<String, Object> map){
		return leftStyleImgsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return leftStyleImgsDao.count(map);
	}
	
	@Override
	public int save(LeftStyleImgsDO leftStyleImgs){
		return leftStyleImgsDao.save(leftStyleImgs);
	}
	
	@Override
	public int update(LeftStyleImgsDO leftStyleImgs){
		return leftStyleImgsDao.update(leftStyleImgs);
	}
	
	@Override
	public int remove(Integer id){
		return leftStyleImgsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return leftStyleImgsDao.batchRemove(ids);
	}

	@Override
	public int saveBatch(Map<String, Object> map) {
		return leftStyleImgsDao.saveBatch(map);
	}

	@Override
	public int removeByLeftId(Integer leftId) {
		return leftStyleImgsDao.removeByLeftId(leftId);
	}
	
}
