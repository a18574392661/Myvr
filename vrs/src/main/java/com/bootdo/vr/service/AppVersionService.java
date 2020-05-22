package com.bootdo.vr.service;

import com.bootdo.vr.domain.AppVersionDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email test@163.com
 * @date 2019-01-07 15:45:31
 */
public interface AppVersionService {
	
	AppVersionDO get(Integer id);
	
	List<AppVersionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AppVersionDO appVersion);
	
	int update(AppVersionDO appVersion);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	AppVersionDO getByClient(String client);
	
	int updateVersion(AppVersionDO versionDo);
}
