package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.domain.ImgclsImgsDO;
import com.bootdo.vrs.service.ImgclsImgsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-12 16:26:34
 */
 
@Controller
@RequestMapping("/vrs/imgclsImgs")
public class ImgclsImgsController {
	@Autowired
	private ImgclsImgsService imgclsImgsService;
	
	@GetMapping()
	@RequiresPermissions("vrs:imgclsImgs:imgclsImgs")
	String ImgclsImgs(String cid, ModelMap map){
	    map.put("cid",cid);

		return "vrs/imgclsImgs/imgclsImgs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:imgclsImgs:imgclsImgs")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ImgclsImgsDO> imgclsImgsList = imgclsImgsService.list(query);
		int total = imgclsImgsService.count(query);
		PageUtils pageUtils = new PageUtils(imgclsImgsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:imgclsImgs:add")
	String add(Integer cid,ModelMap map){
	    map.put("cid",cid);
		return "vrs/imgclsImgs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:imgclsImgs:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ImgclsImgsDO imgclsImgs = imgclsImgsService.get(id);
		model.addAttribute("imgclsImgs", imgclsImgs);
	    return "vrs/imgclsImgs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:imgclsImgs:add")
	public R save(ImgclsImgsDO imgclsImgs){
		if(imgclsImgsService.save(imgclsImgs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:imgclsImgs:edit")
	public R update( ImgclsImgsDO imgclsImgs){

		imgclsImgsService.update(imgclsImgs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:imgclsImgs:remove")
	public R remove( Integer id){
		if(imgclsImgsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}



	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:imgclsImgs:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		imgclsImgsService.batchRemove(ids);
		return R.ok();
	}



}
