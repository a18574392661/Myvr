package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.dao.ImgclsImgsDao;
import com.bootdo.vrs.domain.ImgclsImgsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.bootdo.vrs.service.ImgclsImgsService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class ImgclsImgsServiceImpl implements ImgclsImgsService {
	@Autowired
	private ImgclsImgsDao imgclsImgsDao;
	

	public ImgclsImgsDO get(Integer id){
		return imgclsImgsDao.get(id);
	}
	
	@Override
	public List<ImgclsImgsDO> list(Map<String, Object> map){
		return imgclsImgsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imgclsImgsDao.count(map);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int save(ImgclsImgsDO imgclsImgs){
			int count=0;
			if (this.saveImgsCls(null,imgclsImgs.getCid(),imgclsImgs.getImgsCls(),imgclsImgs.getSort()))
				count=1;


		return count;
	}

	//先删除 在添加
	public boolean saveImgsCls(Integer id,Integer cid,String[] imgsCls,Integer sort){
			//先删除 在添加
			if (id!=null){
				//编辑
				imgclsImgsDao.dleImgClsCid(cid);
			}
			if (imgsCls!=null&&imgsCls.length>0){
				//循环添加
				for (int i = 0; i <imgsCls.length ; i++) {
						ImgclsImgsDO imgclsImgsDO=new ImgclsImgsDO();
						imgclsImgsDO.setCid(cid);
						imgclsImgsDO.setSrc(imgsCls[i]);
						imgclsImgsDO.setSort(sort);
					int count=imgclsImgsDao.save(imgclsImgsDO);
					if (count<=0){
						return  false;

					}				}



			}
			return  true;

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(ImgclsImgsDO imgclsImgs){
		/*int count=0;
		if (this.saveImgsCls(imgclsImgs.getId(),imgclsImgs.getCid(),imgclsImgs.getImgsCls(),imgclsImgs.getSort()))
			count=1;*/

		//编辑只能编辑一个
		return imgclsImgsDao.update(imgclsImgs);
	}



	@Override
	public int remove(Integer id){
		return imgclsImgsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return imgclsImgsDao.batchRemove(ids);
	}
	
}
