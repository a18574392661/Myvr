package com.bootdo.vr.dao;

import com.bootdo.vr.domain.RoomTypeImgsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 房间类型对应的热点图表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 16:26:12
 */
@Mapper
public interface RoomTypeImgsDao {

	RoomTypeImgsDO get(Integer id);
	
	List<RoomTypeImgsDO> list(Map<String,Object> map);
	
	int saveBatch(Map<String,Object> map);
	
	int removeByRoomId(Integer roomId);
	
	int count(Map<String,Object> map);
	
	int save(RoomTypeImgsDO roomTypeImgs);
	
	int update(RoomTypeImgsDO roomTypeImgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
