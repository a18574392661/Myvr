package com.bootdo.vrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.vrs.dao.ImgDetaliesDao;
import com.bootdo.vrs.domain.ImgDetaliesDO;
import com.bootdo.vrs.service.ImgDetaliesService;



@Service
public class ImgDetaliesServiceImpl implements ImgDetaliesService {
	@Autowired
	private ImgDetaliesDao imgDetaliesDao;
	
	@Override
	public ImgDetaliesDO get(Long id){
		return imgDetaliesDao.get(id);
	}
	
	@Override
	public List<ImgDetaliesDO> list(Map<String, Object> map){
		return imgDetaliesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imgDetaliesDao.count(map);
	}
	
	@Override
	public int save(ImgDetaliesDO imgDetalies){
		imgDetalies.setCreatedate(new Date());
		imgDetalies.setStatus(1);
		return imgDetaliesDao.save(imgDetalies);
	}
	
	@Override
	public int update(ImgDetaliesDO imgDetalies){

		imgDetalies.setUpdatedate(new Date());
		return imgDetaliesDao.update(imgDetalies);
	}
	
	@Override
	public int remove(Long id){
		return imgDetaliesDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return imgDetaliesDao.batchRemove(ids);
	}
	
}
