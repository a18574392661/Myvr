package com.bootdo.vr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.vr.domain.AppVersionDO;

/**
 * 
 * @author chglee
 * @email test@163.com
 * @date 2019-01-07 15:45:31
 */
@Mapper
public interface AppVersionDao {

	AppVersionDO get(Integer id);
	
	List<AppVersionDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AppVersionDO appVersion);
	
	int update(AppVersionDO appVersion);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	AppVersionDO getByClient(String client);
	
	int updateVersion(AppVersionDO versionDo);
}
