package com.bootdo.vrs.service.impl;

import com.alibaba.fastjson.JSON;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.LbimgsDao;
import com.bootdo.vrs.domain.LbimgsDO;
import com.bootdo.vrs.service.LbimgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;



@Service
@Transactional
public class LbimgsServiceImpl implements LbimgsService {
	@Autowired
	private LbimgsDao lbimgsDao;

	@Autowired
	private RedisUtil redisUtil;

	
	@Override
	public LbimgsDO get(Integer id){
		return lbimgsDao.get(id);
	}
	
	@Override
	public List<LbimgsDO> list(Map<String, Object> map){
		return lbimgsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lbimgsDao.count(map);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int save(LbimgsDO lbimgs){
		int count=lbimgsDao.save(lbimgs);
		if (count>0){

			this.delRedisMath(lbimgs.getId(),MessageConstantVrs.LBTALL);
		}
		return count;
	}
	
	@Override
	public int update(LbimgsDO lbimgs){
		int count= lbimgsDao.update(lbimgs);
		if (count>0){
			delRedisMath(lbimgs.getId(),MessageConstantVrs.LBTALL);
		}
		return count;
	}
	
	@Override
	public int remove(Integer id){
		int count=lbimgsDao.remove(id);
		if (count>0){
			delRedisMath(id,MessageConstantVrs.LBTALL);
		}

		return count;
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return lbimgsDao.batchRemove(ids);
	}



	//同步缓存
	public  void delRedisMath(Integer id,String key){

				Jedis jedis=null;
		try {
				jedis = redisUtil.getJedis();
				jedis.del(key);
				//查询放进去
				jedis.set(MessageConstantVrs.LBTALL, JSON.toJSONString(lbimgsDao.list(null)));
				if (id!=null){
					jedis.del(MessageConstantVrs.LBTA+id);
					LbimgsDO lbimgsDO=lbimgsDao.get(id);

					if (lbimgsDO!=null){
						jedis.set(MessageConstantVrs.LBTA+id,JSON.toJSONString(lbimgsDO));
					}else {
						//缓存击穿
						jedis.setex(MessageConstantVrs.LBTA+id,60,JSON.toJSONString(lbimgsDO));

					}


				}

		}
		catch (Exception e){
			e.printStackTrace();

		}
		finally {
			if (jedis!=null){
				jedis.close();
			}
		}

	}

}
