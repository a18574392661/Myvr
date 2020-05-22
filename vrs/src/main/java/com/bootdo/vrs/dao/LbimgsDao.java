package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.LbimgsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 轮播图表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 11:12:36
 */
@Mapper
public interface LbimgsDao {

	LbimgsDO get(Integer id);
	
	List<LbimgsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LbimgsDO lbimgs);
	
	int update(LbimgsDO lbimgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

		@Select("select * from vrs_lbimgs where id=${id}")
       LbimgsDO queryByID(@Param("id") String id);
}
