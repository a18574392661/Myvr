package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.ImgsSytDao;
import com.bootdo.vrs.domain.ImgsSytDO;
import com.bootdo.vrs.service.ImgsSytService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
public class ImgsSytServiceImpl implements ImgsSytService {
	@Autowired
	private ImgsSytDao imgsSytDao;
	
	@Override
	public ImgsSytDO get(Integer id){
		return imgsSytDao.get(id);
	}
	
	@Override
	public List<ImgsSytDO> list(Map<String, Object> map){
		return imgsSytDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imgsSytDao.count(map);
	}
	
	@Override
	public int save(ImgsSytDO imgsSyt){
		boolean b=	this.saveImgs(imgsSyt.getImgsCls(),imgsSyt.getCid(),imgsSyt.getSort());
		if (b==false){
				return 0;

		}
		return 1;
	}

	public  boolean saveImgs(String[] imgs,Integer id,Integer sort){
         if (imgs==null&imgs.length<=0){
         	return  true;
		 }

		for (int i = 0; i < imgs.length; i++) {
			ImgsSytDO imgsSytDO=new ImgsSytDO();
			imgsSytDO.setCid(id);
			imgsSytDO.setImg(imgs[i]);
			imgsSytDO.setSort(sort);
			int c=imgsSytDao.save(imgsSytDO);
			if (c<=0){
				return  false;
			}
		}
		return  true;
	}
	
	@Override
	public int update(ImgsSytDO imgsSyt){

		imgsSyt.setUpdatedatee(new Date());
		return imgsSytDao.update(imgsSyt);
	}
	
	@Override
	public int remove(Integer id){
		return imgsSytDao.remove(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int batchRemove(Integer[] ids,Integer cid){
		imgsSytDao.delProTjimg(cid);
		//添加
		if (ids!=null&&ids.length>0){
			if (ids.length>2){
				throw  new RuntimeException(MessageConstantVrs.SETPROSIMG);
			}

			for (Integer id : ids) {
				ImgsSytDO imgsSytDO=new ImgsSytDO();
				imgsSytDO.setSort(1);
				imgsSytDO.setCid(cid);
				imgsSytDO.setPid(id);
				imgsSytDao.save(imgsSytDO);
			}

		}
		return imgsSytDao.batchRemove(ids);
	}

	@Override
	public PageInfo<ImgsSytDO> querySyImg(Integer proid, Integer size,Integer page) {
		PageInfo<ImgsSytDO> pageInfo=null;
		PageHelper.startPage(page,size);
		List<ImgsSytDO> list=imgsSytDao.querySyImg(proid);
		pageInfo=new PageInfo<ImgsSytDO>(list);
		if (pageInfo.getNextPage()<=0){
			pageInfo.setNextPage(pageInfo.getPages());
		}
		if (pageInfo.getPrePage()<=0){
			pageInfo.setPrePage(1);
		}


		return pageInfo;
	}

}
