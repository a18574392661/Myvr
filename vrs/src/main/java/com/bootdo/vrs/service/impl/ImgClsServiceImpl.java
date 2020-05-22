package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.ImgClsDao;
import com.bootdo.vrs.domain.ImgClsDO;
import com.bootdo.vrs.service.ImgClsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
@Transactional
public class ImgClsServiceImpl implements ImgClsService {
	@Autowired
	private ImgClsDao imgClsDao;
	
	@Override
	public ImgClsDO get(Long id){
		return imgClsDao.get(id);
	}
	
	@Override
	public List<ImgClsDO> list(Map<String, Object> map){
		return imgClsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return imgClsDao.count(map);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int save(ImgClsDO imgCls){
		int sum=0;
		if (imgCls.getRadios()==2){
			//批量添加三级分类
			String[] sz=imgCls.getNames();
			if (sz!=null&&sz.length>0){
				//批量添加
				for (String s : sz) {
					ImgClsDO ims=new ImgClsDO();
					ims.setPid(imgCls.getPid());
					ims.setStatus(1+"");
					ims.setCreatedate(new Date());
					ims.setName(s);
					ims.setCid(imgCls.getCid());
				 	sum+=imgClsDao.save(ims);
				}
			}

			return  sum>=sz.length?1:0;
		}
		else {
			//一级 或者 二级
			imgCls.setCreatedate(new Date());
			imgCls.setStatus(1+"");
			if (imgCls.getPid()==null){
				imgCls.setPid(0);
			}
		}
			 String[] sz=imgCls.getNames();
			for (int i = 0; i <sz.length ; i++) {
				System.out.println(sz[i]);
			}

		return imgClsDao.save(imgCls);
	}
	
	@Override
	public int update(ImgClsDO imgCls){

		imgCls.setUpdatedate(new Date());
		return imgClsDao.update(imgCls);
	}
	
	@Override
	public int remove(Long id){
		//查询 父分类不能删除 我
		List<ImgClsDO> list=imgClsDao.imgClsPars(id+"");
		if (list!=null&&list.size()>0){
			throw  new RuntimeException(MessageConstantVrs.IMGCLSDEL_ERROR);
		}
		//查询子集下面有图片
		int count=imgClsDao.getClsChildPro(id);
		if (count>0){
			throw  new RuntimeException(MessageConstantVrs.CLS_CHILD_PRO);
		}
		//上传
		return imgClsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return imgClsDao.batchRemove(ids);
	}

	@Override
	public List<ImgClsDO> imgClsPars(String pid) {
		List<ImgClsDO> list=imgClsDao.imgClsPars(pid);
		for (ImgClsDO imgClsDO : list) {
			List<ImgClsDO> child=imgClsDao.imgClsPars(imgClsDO.getId()+"");
			imgClsDO.setChilds(child);

		}

		return list;
	}

	@Override
	public String[] queryProCeebnCls(Integer id) {

		return imgClsDao.queryProCeebnCls(id);
	}

	@Override
	public List<Map<String, Object>> getAllCategory(Map<String,String> param) {
		// TODO Auto-generated method stub
		
		return imgClsDao.getAllCategory(param);
	}

	@Override
	public List<Map<String, Object>> getAllCategorySub(Map<String, String> param) {
		// TODO Auto-generated method stub
		return imgClsDao.getAllCategorySub(param);
	}

	@Override
	public List<Map<String, Object>> qeuryCategoryByProId(Integer id) {
		// TODO Auto-generated method stub
		
		return imgClsDao.qeuryCategoryByProId(id);
	}

	@Override
	public List<ImgClsDO> getThreeImgClsDo() {
		//查询三级1分类
		List<ImgClsDO> list=imgClsDao.imgClsPars("0");
		getChild(list);

		return list;
	}

	public  void getChild(List<ImgClsDO> imgClsDOList){
		for (ImgClsDO imgcls : imgClsDOList) {
			List<ImgClsDO> listChild=imgClsDao.imgClsPars(imgcls.getId()+"");
			if (listChild!=null&&listChild.size()>0){
				this.getChild(listChild);

			}
			imgcls.setChilds(listChild);
		}
	}

}
