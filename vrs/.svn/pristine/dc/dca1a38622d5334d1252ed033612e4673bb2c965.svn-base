package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.ImgclsImgsDO;
import com.bootdo.vrs.domain.ProDO;
import com.bootdo.vrs.domain.ProDo2;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 海报价格图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 10:19:40
 */
public interface ProService {
	
	ProDO get(Integer id);
	
	List<ProDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProDO pro);
	
	int update(ProDO pro);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	//分类查询
	List<ProDO> getAll(Map<String,String> map);
	
	Map<String,Object> queryIndex();


	List<ProDo2> queryTitleLimit(Integer id);




	List<Map<String,String>> getAllMune(Map<String,Object> map);

	PageInfo<ProDO> queryTypeProCls(String parenCls, String typeid,Integer page);


	PageInfo<ProDO> quertSearchPro(Integer size,Integer page,String name);

	PageInfo<ImgclsImgsDO> queryServiceDetali(Integer proid,Integer page,Integer size);

	 List<ProDO> listProTie(Map<String,Object> map);

	 int saves(ProDO proDO);

	PageInfo<ProDO> getTitleLimt(Integer page,String tid);
}
