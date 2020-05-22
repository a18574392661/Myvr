package com.bootdo.vrs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.vrs.dao.ImgCls3Dao;
import com.bootdo.vrs.domain.ImgCls3DO;
import com.bootdo.vrs.service.ImgCls3Service;



@Service
public class ImgCls3ServiceImpl implements ImgCls3Service {
	@Autowired
	private ImgCls3Dao imgCls3Dao;
	
	@Override
	public ImgCls3DO get(Integer id){
		return imgCls3Dao.get(id);
	}
	
	@Override
	public List<ImgCls3DO> list(Map<String, Object> map){
		return imgCls3Dao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imgCls3Dao.count(map);
	}
	
	@Override
	public int save(ImgCls3DO imgCls3){
		//保存当前时间
		imgCls3.setUpdateDate(new Date());
		return imgCls3Dao.save(imgCls3);
	}
	
	@Override
	public int update(ImgCls3DO imgCls3){
		//保存当前时间
		imgCls3.setUpdateDate(new Date());
		return imgCls3Dao.update(imgCls3);
	}
	
	@Override
	public int remove(Integer id){
		return imgCls3Dao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return imgCls3Dao.batchRemove(ids);
	}

	@Override
	public ImgCls3DO getCategory(Integer id) {
		// TODO Auto-generated method stub
		return imgCls3Dao.getCategory(id);
	}

	@Override
	public List<Map<String, String>> contains(Map<String, Object> rootMap) {
		// TODO Auto-generated method stub
		return imgCls3Dao.contains(rootMap);
	}


	
}
