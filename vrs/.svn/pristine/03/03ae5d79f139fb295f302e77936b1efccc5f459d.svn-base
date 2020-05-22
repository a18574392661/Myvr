package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.AppVersionDao;
import com.bootdo.vr.domain.AppVersionDO;
import com.bootdo.vr.service.AppVersionService;



@Service
public class AppVersionServiceImpl implements AppVersionService {
	@Autowired
	private AppVersionDao appVersionDao;
	
	@Override
	public AppVersionDO getByClient(String client) {
		return appVersionDao.getByClient(client);
	}
	
	@Override
	public int updateVersion(AppVersionDO versionDo) {
		return appVersionDao.updateVersion(versionDo);
	}
	
	@Override
	public AppVersionDO get(Integer id){
		return appVersionDao.get(id);
	}
	
	@Override
	public List<AppVersionDO> list(Map<String, Object> map){
		return appVersionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return appVersionDao.count(map);
	}
	
	@Override
	public int save(AppVersionDO appVersion){
		return appVersionDao.save(appVersion);
	}
	
	@Override
	public int update(AppVersionDO appVersion){
		return appVersionDao.update(appVersion);
	}
	
	@Override
	public int remove(Integer id){
		return appVersionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return appVersionDao.batchRemove(ids);
	}
	
}
