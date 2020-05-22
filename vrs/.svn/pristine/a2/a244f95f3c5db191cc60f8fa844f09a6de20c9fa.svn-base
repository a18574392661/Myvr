package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.IpgetCountDO;

import java.util.List;
import java.util.Map;

import com.bootdo.vrs.domain.ProDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 图片浏览量
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-21 11:56:29
 */
@Mapper
public interface IpgetCountDao {

	IpgetCountDO get(Long id);
	
	List<IpgetCountDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(IpgetCountDO ipgetCount);
	
	int update(IpgetCountDO ipgetCount);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	@Select("\n" +
			"\t\tSELECT * from vrs_pro\n" +
			"\t\tWHERE id not in(SELECT proid from vrs_ipget_count )")
	List<ProDO> queryNotPro();


	@Select("select count(1) from vrs_ipget_count where proid=#{proid}")
	int queryProCount(@Param("proid") Integer id);


	@Update("update vrs_ipget_count set count=count+1 where proid=#{proid}")
	int editCount(@Param("proid") String proid);

	@Select("select * from vrs_ipget_count where proid=#{proid}")
	IpgetCountDO queryPro(@Param("proid") Integer proid);
}
