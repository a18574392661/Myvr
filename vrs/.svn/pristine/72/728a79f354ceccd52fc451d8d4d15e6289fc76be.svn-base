package com.bootdo.system.dao;

import com.bootdo.system.domain.UserDO;
import com.bootdo.vrs.domain.PayorderLogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);
	
	List<UserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();
	
	
	UserDO queryUserName(UserDO userDO);
	int queryUserNameDisble(Map<String,Object> map);

	@Select("select * from sys_user where username=#{username}")
	UserDO queryVRUserName(@Param("username") String username);



	int queryTel(@Param("tel") String tel,@Param("id") String id);


	int queryEmall(@Param("eml") String tel,@Param("id") String id);


	int queryUserNames(@Param("username") String username,@Param("id") String id);

	@Select("select  pid from  vrs_use_parent where uid=#{uid}")
	public Long getPid(@Param("uid") Long uid);


	public  int editUserVip(PayorderLogDO payorderLogDO);

	@Select("select * from sys_user where isVip=1 and idfind='vr' ")
	List<com.bootdo.vrs.domain.UserDO> queryUserVip();

	@Update("update sys_user set vipDate=null, isVip=0 where user_id=#{userId}")
	int editVip(com.bootdo.vrs.domain.UserDO userDO);


	@Select("select count(1) FROM sys_user WHERE mobile=#{mobile}\n" +
			"or username=#{username}")
	int queryUsernameAndPwd(UserDO userDO);
}
