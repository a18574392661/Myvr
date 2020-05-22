package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.dao.ColorImgclsDao;
import com.bootdo.vrs.domain.ColorImgclsDO;
import com.bootdo.vrs.service.ColorImgclsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ColorImgclsServiceImpl implements ColorImgclsService {
	@Autowired
	private ColorImgclsDao colorImgclsDao;
	
	@Override
	public ColorImgclsDO get(Long id){
		return colorImgclsDao.get(id);
	}
	
	@Override
	public List<ColorImgclsDO> list(Map<String, Object> map){
		return colorImgclsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return colorImgclsDao.count(map);
	}
	
	@Override
	public int save(ColorImgclsDO colorImgcls){
		return colorImgclsDao.save(colorImgcls);
	}
	
	@Override
	public int update(ColorImgclsDO colorImgcls){
		return colorImgclsDao.update(colorImgcls);
	}
	
	@Override
	public int remove(Long id){
		return colorImgclsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return colorImgclsDao.batchRemove(ids);
	}
	
}
