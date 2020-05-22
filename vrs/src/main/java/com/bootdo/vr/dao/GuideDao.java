package com.bootdo.vr.dao;

import com.bootdo.vr.domain.GuideDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 引导图
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-04 14:16:25
 */
@Mapper
public interface GuideDao {

	GuideDO get(Integer id);
	
	List<GuideDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GuideDO guide);
	
	int update(GuideDO guide);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
