package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.UserShopDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 用户加入图片购物车表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-21 22:19:09
 */
@Mapper
public interface UserShopDao {

	UserShopDO get(Long id);
	
	List<UserShopDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserShopDO userShop);
	
	int update(UserShopDO userShop);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	@Select("select count(1) from vrs_user_shop WHERE uid=#{uid} and proid=#{proid}")
	int queryUserShop(UserShopDO userShopDO);

	@Update("update vrs_user_shop set count=count+1,name=#{name} WHERE uid=#{uid} and proid=#{proid} ")
	int editUserShop(UserShopDO userShopDO);
}

