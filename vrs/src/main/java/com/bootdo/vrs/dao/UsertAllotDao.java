package com.bootdo.vrs.dao;


import com.bootdo.vrs.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-21 09:51:24
 */
@Mapper
public interface UsertAllotDao {


	UserDO get(Long userId);
	
	List<UserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long user_id);
	
	int batchRemove(Long[] userIds);

	//查询名下的子账号 以及判断是否拥有这张图
	List<UserDO> getUserPro(Map<String,Object> paremts);


	List<UserDO> userTree();

	int saveMyUserNamecount(Map<String,Object> map);

	//查询子级
	@Select("select uid from vrs_use_parent where pid=#{pid}")
	List<Integer> getUids(@Param("pid") Long pid);

	@Select("select * from sys_user where user_id in ${ids}")
	List<UserDO> getUsers(@Param("ids") String ids);


	List<UserDO> queryList(@Param("uid") String uid);

	//分配图的子账号
	List<UserDO> getChildUser(@Param("uid") String uid);



}
