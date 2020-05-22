package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.domain.ColorImgclsDO;
import com.bootdo.vrs.service.ColorImgclsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 色卡对应全景图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-29 18:09:16
 */

@Controller
@RequestMapping("/vrs/colorImgcls")
public class ColorImgclsController {
	@Autowired
	private ColorImgclsService colorImgclsService;
	
	@GetMapping()
	@RequiresPermissions("vrs:colorImgcls:colorImgcls")
	String ColorImgcls(Model model,String proid)

	{
		model.addAttribute("proid",proid);
	    return "vrs/colorImgcls/colorImgcls";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:colorImgcls:colorImgcls")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ColorImgclsDO> colorImgclsList = colorImgclsService.list(query);
		int total = colorImgclsService.count(query);
		PageUtils pageUtils = new PageUtils(colorImgclsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:colorImgcls:add")
	String add(ModelMap map,String proid)

	{
		map.put("proid",proid);
	    return "vrs/colorImgcls/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:colorImgcls:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ColorImgclsDO colorImgcls = colorImgclsService.get(id);
		model.addAttribute("colorImgcls", colorImgcls);
	    return "vrs/colorImgcls/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:colorImgcls:add")
	public R save( ColorImgclsDO colorImgcls){
		if(colorImgclsService.save(colorImgcls)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:colorImgcls:edit")
	public R update( ColorImgclsDO colorImgcls){
		colorImgcls.setUpdatedate(new Date());
		colorImgclsService.update(colorImgcls);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:colorImgcls:remove")
	public R remove( Long id){
		if(colorImgclsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:colorImgcls:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		colorImgclsService.batchRemove(ids);
		return R.ok();
	}


	@RequestMapping("/upcolorcars")
	public  String upcolorcars(String cid,ModelMap map){
		map.put("cid",cid);

		return  "vrs/colorImgcls/upcolorcars";

	}
	
}
