package com.bootdo.system.dao;

import com.bootdo.system.domain.RsDO;
import com.bootdo.system.domain.TestDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 测试案例
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-07 16:24:49
 */
@Mapper
public interface TestDao {

	TestDO get(Long id);
	
	List<TestDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TestDO test);
	
	int update(TestDO test);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	@Select("select * from vr_rs")
	List<RsDO> queryRoles();
}
