package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.dao.ImgclsImgsDao;
import com.bootdo.vrs.dao.ProDao;
import com.bootdo.vrs.dao.TitleClsDao;
import com.bootdo.vrs.domain.*;
import com.bootdo.vrs.service.ImgClsService;
import com.bootdo.vrs.service.ImgDetaliesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-12 13:25:04
 */
 
@Controller
@RequestMapping("/vrs/imgDetalies")
public class ImgDetaliesController {
	@Autowired
	private ImgDetaliesService imgDetaliesService;

@Autowired
private ImgclsImgsDao imgclsImgsDao;

	@Autowired
	private ImgClsService imgClsService;
	@Autowired
	private ProDao proDao;

	@Autowired
	private TitleClsDao titleClsDao;

	@GetMapping("/to_detail")
	String to_detail(ModelMap map,Integer cid){
		String tbname="";
		map.put("cid",cid);
		List<ImgClsDO> list=new ArrayList();
		//查询分类
		ProDO proDO=proDao.get(cid);
		String img=proDO.getImg().substring(proDO.getImg().lastIndexOf("/")+1,proDO.getImg().indexOf("."));
		proDO.setQjtfile(img+".tiles");
		map.put("pro",proDO);
		if (proDO!=null){
			//查询出判断分类
			TitleClsDO titleClsDO=titleClsDao.queryProTypeid(cid+"");
			map.put("ctitle",titleClsDO.getName());
			//查
			ImgClsDO imgClsDO=null;
				 if (titleClsDO.getTypeid()==2){
					tbname="vrs_img_cls2";
					imgClsDO=titleClsDao.getClsName(tbname,proDO.getCid()+"");
				}
				else if(titleClsDO.getTypeid()==3){
					tbname="vrs_img_cls4";
					imgClsDO=titleClsDao.getClsName(tbname,proDO.getCid()+"");
				}
				else if (titleClsDO.getTypeid()==4){
					tbname="vrs_img_cls3";
					imgClsDO=titleClsDao.getClsName(tbname,proDO.getCid()+"");
				}

				list.add(imgClsDO);


				if (titleClsDO.getTypeid()==1){
					list.remove(0);
					tbname="vrs_img_cls2";
					list=titleClsDao.queryGet(cid);
				}
				map.put("clsList",list);

				//产品对应多个标签
			List<TitleClsDO> list1=titleClsDao.getproAll(cid+"");
			map.put("typeList",list1);

	}else{
			//没有这张图片

		}
		return "vrs/userImgcls/myProDetailsimg";
	}


	@GetMapping()
	@RequiresPermissions("vrs:imgDetalies:imgDetalies")
	String ImgDetalies(ModelMap modelMap){
		modelMap.put("list",imgClsService.list(null));
		return "vrs/imgDetalies/imgDetalies";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ImgDetaliesDO> imgDetaliesList = imgDetaliesService.list(query);
		int total = imgDetaliesService.count(query);
		PageUtils pageUtils = new PageUtils(imgDetaliesList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/myproImgs")
	public List<ImgclsImgsDO> myproImgs(Integer cid){
		//查询列表数据
		Map m=new HashMap();
		m.put("cid",cid+"");
		List<ImgclsImgsDO> imgDetaliesList = imgclsImgsDao.list(m);
		return imgDetaliesList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:imgDetalies:add")
	String add(ModelMap modelMap){
		modelMap.put("list",imgClsService.list(null));
	    return "vrs/imgDetalies/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:imgDetalies:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ImgDetaliesDO imgDetalies = imgDetaliesService.get(id);
		model.addAttribute("imgDetalies", imgDetalies);
		model.addAttribute("list",imgClsService.list(null));
	    return "vrs/imgDetalies/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:imgDetalies:add")
	public R save( ImgDetaliesDO imgDetalies){
		if(imgDetaliesService.save(imgDetalies)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:imgDetalies:edit")
	public R update( ImgDetaliesDO imgDetalies){
		imgDetaliesService.update(imgDetalies);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:imgDetalies:remove")
	public R remove( Long id){
		if(imgDetaliesService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:imgDetalies:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		imgDetaliesService.batchRemove(ids);
		return R.ok();
	}
	
}
