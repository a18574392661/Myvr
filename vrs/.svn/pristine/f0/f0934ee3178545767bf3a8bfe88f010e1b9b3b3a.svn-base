package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.LogImgsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-18 16:09:22
 */
@Mapper
public interface LogImgsDao {

	LogImgsDO get(Integer id);
	
	List<LogImgsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LogImgsDO logImgs);
	
	int update(LogImgsDO logImgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);


	@Select("select * from vrs_log_imgs \n" +
			"ORDER BY sort,updateDate\n" +
			"LIMIT 0 ,10")
	List<LogImgsDO> queryLimitLog();
}
