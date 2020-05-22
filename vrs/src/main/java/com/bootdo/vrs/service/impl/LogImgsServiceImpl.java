package com.bootdo.vrs.service.impl;

import com.bootdo.school.util.RedisUtil;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.LogImgsDao;
import com.bootdo.vrs.domain.LogImgsDO;
import com.bootdo.vrs.service.LogImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class LogImgsServiceImpl implements LogImgsService {
	@Autowired
	private LogImgsDao logImgsDao;

	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public LogImgsDO get(Integer id){
		return logImgsDao.get(id);
	}
	
	@Override
	public List<LogImgsDO> list(Map<String, Object> map){
		return logImgsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return logImgsDao.count(map);
	}
	
	@Override
	public int save(LogImgsDO logImgs){
		logImgs.setCreatedate(new Date());
		delRedis();
		int save = 0;
		String src = logImgs.getSrc();
		save += logImgsDao.save(logImgs);
	/*	if(!StringUtils.isEmpty(src)) {
			String[] split = src.split(",");
			for (String str : split) {
				logImgs.setSrc(str);
				save += logImgsDao.save(logImgs);
			}
		}*/
		return save;
	}
	
	@Override
	public int update(LogImgsDO logImgs){
		logImgs.setUpdatedate(new Date());
		delRedis();
		return logImgsDao.update(logImgs);
	}
	
	@Override
	public int remove(Integer id){

		delRedis();
		return logImgsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		delRedis();
		return logImgsDao.batchRemove(ids);
	}


	@Override
	public List<LogImgsDO> queryLimitLog() {


		return logImgsDao.queryLimitLog();
	}


	public  void delRedis(){
		Jedis jedis=redisUtil.getJedis();
		try {
			jedis.del(MessageConstantVrs.LOGVALUE);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (jedis!=null)
				jedis.close();

		}
	}

}
