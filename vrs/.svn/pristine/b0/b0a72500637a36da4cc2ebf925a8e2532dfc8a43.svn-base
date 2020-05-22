package com.bootdo.ts.dao;

import com.bootdo.ts.domain.ZwDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:47:40
 */
@Mapper
public interface ZwDao {

	ZwDO get(Integer id);
	
	List<ZwDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ZwDO zw);
	
	int update(ZwDO zw);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	//查询当前课程下面的 座位
	@Select("select z.*,uc.id,IFNULL(uc.id,0) as 'checked',uc.uname as 'uname' from t_zw z\n" +
			"\t\t left join t_u_cids uc\n" +
			"\t\t on z.id=uc.zid where z.cid =#{cid}  and z.status=1  ORDER BY z.id asc ")
	List<ZwDO> queryDqZw(@Param("cid") String cid);


	//第一个座位
	@Select(" SELECT * from t_zw z\n" +
			" WHERE z.cid=#{cid} \n" +
			" ORDER BY z.id desc\n" +
			" LIMIT 1")
	ZwDO querybyCurrlogOne(@Param("cid") Long id);
}
