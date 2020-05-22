package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ImgclsImgsDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-12 16:26:34
 */
@Mapper
public interface ImgclsImgsDao {

	ImgclsImgsDO get(Integer id);
	
	List<ImgclsImgsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ImgclsImgsDO imgclsImgs);
	
	int update(ImgclsImgsDO imgclsImgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Delete("delete from vrs_imgcls_imgs from where cid=#{cid}")
	int dleImgClsCid(@Param("cid") Integer cid);
}
