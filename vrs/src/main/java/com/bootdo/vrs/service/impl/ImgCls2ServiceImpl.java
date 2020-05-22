package com.bootdo.vrs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.vrs.dao.ImgCls2Dao;
import com.bootdo.vrs.domain.ImgCls2DO;
import com.bootdo.vrs.domain.ImgClsDO;
import com.bootdo.vrs.service.ImgCls2Service;



@Service
public class ImgCls2ServiceImpl implements ImgCls2Service {
	@Autowired
	private ImgCls2Dao imgCls2Dao;
	
	@Override
	public ImgCls2DO get(Integer id){
		return imgCls2Dao.get(id);
	}
	
	@Override
	public List<ImgCls2DO> list(Map<String, Object> map){
		return imgCls2Dao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imgCls2Dao.count(map);
	}
	
	@Override
	public int save(ImgCls2DO imgCls2){
		//设置当前时间
		imgCls2.setUpdateDate(new Date());
		//当前分类是是否是顶级分类
		Integer pid = imgCls2.getPid();
		if(pid == null) {
			imgCls2.setPid(0);
		}
		return imgCls2Dao.save(imgCls2);
	}
	
	@Override
	public int update(ImgCls2DO imgCls2){
		//设置当前时间
		imgCls2.setUpdateDate(new Date());
		return imgCls2Dao.update(imgCls2);
	}
	
	@Override
	public int remove(Integer id){
		return imgCls2Dao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return imgCls2Dao.batchRemove(ids);
	}

	@Override
	public ImgCls2DO getCategory(Integer id) {
		// TODO Auto-generated method stub
		return imgCls2Dao.getCategory(id);
	}

	@Override
	public List<Map<String, Object>> queryTableCls(String tableName) {
		return imgCls2Dao.queryTableCls(tableName);
	}

	@Override
	public List<Map<String, String>> getMenu() {
		// TODO Auto-generated method stub
		return imgCls2Dao.getMenu();
	}



	@Override
	public List<Map<String, String>> contains(Map<String,Object> rootMap) {
		// TODO Auto-generated method stub
		return imgCls2Dao.contains(rootMap);
	}

	@Override
	public List<ImgCls2DO> listTopThree(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return imgCls2Dao.listTopThree(params);
	}

	@Override
	public List<ImgClsDO> imgClsPars(String pid) {
		// TODO Auto-generated method stub
		return imgCls2Dao.imgClsPars(pid);
	}
	
}
