package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ImgsSytDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 缩月图标
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-18 09:59:38
 */
@Mapper
public interface ImgsSytDao {

	ImgsSytDO get(Integer id);
	
	List<ImgsSytDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ImgsSytDO imgsSyt);
	
	int update(ImgsSytDO imgsSyt);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);


	List<ImgsSytDO> querySyImg(@Param("proid") Integer proid);

	@Select("select count(1) from vrs_imgs_syt where cid=#{cid}")
	int getProTjimg(@Param("cid") Integer cid);

	@Delete("delete  from vrs_imgs_syt where cid=#{cid}")
	int delProTjimg(@Param("cid") Integer cid);
}
