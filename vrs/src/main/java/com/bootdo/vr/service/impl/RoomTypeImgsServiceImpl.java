package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.RoomTypeImgsDao;
import com.bootdo.vr.domain.RoomTypeImgsDO;
import com.bootdo.vr.service.RoomTypeImgsService;



@Service
public class RoomTypeImgsServiceImpl implements RoomTypeImgsService {
	@Autowired
	private RoomTypeImgsDao roomTypeImgsDao;
	
	@Override
	public RoomTypeImgsDO get(Integer id){
		return roomTypeImgsDao.get(id);
	}
	
	@Override
	public List<RoomTypeImgsDO> list(Map<String, Object> map){
		return roomTypeImgsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roomTypeImgsDao.count(map);
	}
	
	@Override
	public int save(RoomTypeImgsDO roomTypeImgs){
		return roomTypeImgsDao.save(roomTypeImgs);
	}
	
	@Override
	public int update(RoomTypeImgsDO roomTypeImgs){
		return roomTypeImgsDao.update(roomTypeImgs);
	}
	
	@Override
	public int remove(Integer id){
		return roomTypeImgsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return roomTypeImgsDao.batchRemove(ids);
	}

	@Override
	public int saveBatch(Map<String, Object> map) {
		return roomTypeImgsDao.saveBatch(map);
	}

	@Override
	public int removeByRoomId(Integer roomId) {
		return roomTypeImgsDao.removeByRoomId(roomId);
	}
	
}
