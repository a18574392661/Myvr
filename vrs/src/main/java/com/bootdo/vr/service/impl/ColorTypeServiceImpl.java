package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.ColorTypeDao;
import com.bootdo.vr.domain.ColorTypeDO;
import com.bootdo.vr.service.ColorTypeService;



@Service
public class ColorTypeServiceImpl implements ColorTypeService {
	@Autowired
	private ColorTypeDao colorTypeDao;
	
	@Override
	public List<Map<String, Object>> queryForProductTree(Integer[] ids){
		return colorTypeDao.queryForProductTree(ids);
	}
	
	@Override
	public ColorTypeDO get(Integer id){
		return colorTypeDao.get(id);
	}
	
	@Override
	public List<ColorTypeDO> list(Map<String, Object> map){
		return colorTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return colorTypeDao.count(map);
	}
	
	@Override
	public int save(ColorTypeDO colorType){
		return colorTypeDao.save(colorType);
	}
	
	@Override
	public int update(ColorTypeDO colorType){
		return colorTypeDao.update(colorType);
	}
	
	@Override
	public int remove(Integer id){
		return colorTypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return colorTypeDao.batchRemove(ids);
	}
	
}
