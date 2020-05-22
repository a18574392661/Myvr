package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ProDO;
import com.bootdo.vrs.domain.UserImgclsDO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 加入我的云视
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-22 19:52:53
 */
@Mapper
public interface UserImgclsDao {

	UserImgclsDO get(Integer id);
	
	List<UserImgclsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserImgclsDO userImgcls);
	
	int update(UserImgclsDO userImgcls);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select count(1)  from  vrs_user_imgcls where uid=#{uid}")
	int getUserVrPro(@Param("uid") Integer uid);

	@Select("select count(1)  from  vrs_user_imgcls where uid=#{uid} and cid=#{cid}")
	int getUserProCount(UserImgclsDO userImgclsDO);

	@Select("select count(1) from vrs_user_imgcls where uid=#{uid}")
	int getUserImgCount(@Param("uid") Long userid);

	@Delete("delete from vrs_user_imgcls where uid=#{uid} and cid=#{cid}")
  int removeUserImg(@Param("uid") String uid,@Param("cid") String cid);

	List<UserImgclsDO> getUserImg(@Param("uid") String uid);

	@Delete("delete from vrs_user_imgcls where uid=#{uid}")
	int delUserImg(@Param("uid") String uid);

	@Insert("insert into vrs_user_imgcls values(null,#{uid},#{imgid},now(),#{name})")
	int insertUserimgs(Map<String,Object> maps);

	@Select("select count(1) from vrs_user_imgcls where uid!=#{uid} and cid=#{id}")
	int searchUserPro(ProDO proDO);
}
