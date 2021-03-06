package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.ImgClsDao;
import com.bootdo.vrs.domain.ImgClsDO;
import com.bootdo.vrs.domain.TitleClsDO;
import com.bootdo.vrs.service.ImgClsService;
import com.bootdo.vrs.service.TitleClsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-11 21:32:31
 */
 
@Controller
@RequestMapping("/vrs/imgCls")
public class ImgClsController {
	@Autowired
	private ImgClsService imgClsService;

	@Autowired
	private TitleClsService titleClsService;

	@Autowired
	private ImgClsDao imgClsDao;

	@GetMapping()
	@RequiresPermissions("vrs:imgCls:imgCls")
	String ImgCls(){
	    return "vrs/imgCls/imgCls";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:imgCls:imgCls")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//params.put("pid",0);
		//查询列表数据
        Query query = new Query(params);
		List<ImgClsDO> imgClsList = imgClsService.list(query);
		for (int i = 0; i <imgClsList.size() ; i++) {
			ImgClsDO imgClsDO=imgClsDao.get(Long.parseLong(imgClsList.get(i).getPid()+""));
			if (imgClsDO!=null){
				imgClsList.get(i).setPname(imgClsDO.getName());

			}

		/*	List<ImgClsDO> child = imgClsService.imgClsPars(imgClsList.get(i).getId()+"");
			imgClsList.get(i).setChilds(child);*/
		}
		int total = imgClsService.count(query);
		PageUtils pageUtils = new PageUtils(imgClsList, total);
		return pageUtils;
	}

	
	@GetMapping("/add")
	@RequiresPermissions("vrs:imgCls:add")
	String add(ModelMap map){

	    //查询1定制设计的数据
		TitleClsDO titleClsDO=titleClsService.queryTypeId(MessageConstantVrs.PARCLASSDINZHI);
		map.put("type",titleClsDO);

		return "vrs/imgCls/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:imgCls:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ImgClsDO imgCls = imgClsService.get(id);
		TitleClsDO titleClsDO=titleClsService.queryTypeId(MessageConstantVrs.PARCLASSDINZHI);
		model.addAttribute("type", titleClsDO);
		model.addAttribute("lev",1);
		//判断是几级分类
		if (imgCls.getPid()!=0){
			ImgClsDO p1Img =imgClsService.get(Long.parseLong(imgCls.getPid()+""));
			if (p1Img.getPid()!=0){
				//三级
				model.addAttribute("lev",3);
				//一级id
				model.addAttribute("p2id",p1Img.getPid());
			}
		}
		model.addAttribute("imgCls", imgCls);
	    return "vrs/imgCls/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:imgCls:add")
	public R save( ImgClsDO imgCls){
		if(imgClsService.save(imgCls)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:imgCls:edit")
	public R update( ImgClsDO imgCls){
		imgClsService.update(imgCls);
		return R.ok();
	}
	
	/**f
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:imgCls:remove")
	public R remove( Long id){
		try {
			if(imgClsService.remove(id)>0){
				return R.ok();
			}
		}
		catch (RuntimeException e){

			e.printStackTrace();
			return 	R.error(e.getMessage());
		}
		catch (Exception e){

			e.printStackTrace();
		}

		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:imgCls:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		imgClsService.batchRemove(ids);
		return R.ok();
	}

	//查所有pid
	@RequestMapping("/imgClsPars")
	@ResponseBody
	public  List<ImgClsDO> imgClsPars(String pid){

		return  imgClsService.imgClsPars(pid);

	}
	
	/**
	 * 查询所有的分类,状态为1
	 */
	@RequestMapping("/getAllCategoryPrimary")
	@ResponseBody
	public List<Map<String,Object>> getAllCategoryPrimary(@RequestParam Map<String,String> param){

		Map<String,Object> rootMap = new HashMap<String,Object>();
		List<Map<String, Object>> allCategory = imgClsService.getAllCategory(param);
		return  allCategory;
	}
	
	@RequestMapping("/getAllCategorySub")
	@ResponseBody
	public List<Map<String,Object>> getAllCategorySub(@RequestParam Map<String,String> param){
		
		//添加二级分类
		List<Map<String, Object>> allCategorySub = imgClsService.getAllCategorySub(param);
		return  allCategorySub;
	}


}
