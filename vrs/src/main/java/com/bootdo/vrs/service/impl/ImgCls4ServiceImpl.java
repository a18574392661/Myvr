package com.bootdo.vrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.vrs.dao.ImgCls4Dao;
import com.bootdo.vrs.domain.ImgCls4DO;
import com.bootdo.vrs.service.ImgCls4Service;



@Service
public class ImgCls4ServiceImpl implements ImgCls4Service {
	@Autowired
	private ImgCls4Dao imgCls4Dao;
	
	@Override
	public ImgCls4DO get(Integer id){
		return imgCls4Dao.get(id);
	}
	
	@Override
	public List<ImgCls4DO> list(Map<String, Object> map){
		return imgCls4Dao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imgCls4Dao.count(map);
	}
	
	@Override
	public int save(ImgCls4DO imgCls4){
		//保存当前时间
		imgCls4.setUpdateDate(new Date());
		return imgCls4Dao.save(imgCls4);
	}
	
	@Override
	public int update(ImgCls4DO imgCls4){
		//保存当前时间
		imgCls4.setUpdateDate(new Date());
		return imgCls4Dao.update(imgCls4);
	}
	
	@Override
	public int remove(Integer id){
		return imgCls4Dao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return imgCls4Dao.batchRemove(ids);
	}

	@Override
	public ImgCls4DO getCategory(Integer id) {
		// TODO Auto-generated method stub
		return imgCls4Dao.getCategory(id);
	}

	@Override
	public List<Map<String, String>> contains(Map<String, Object> rootMap) {
		// TODO Auto-generated method stub
		return imgCls4Dao.contains(rootMap);
	}
}
