package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.domain.LbimgsDO;
import com.bootdo.vrs.service.LbimgsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 轮播图表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 11:12:36
 */
 
@Controller
@RequestMapping("/vrs/lbimgs")
public class LbimgsController {
	@Autowired
	private LbimgsService lbimgsService;


	
	@GetMapping()
	@RequiresPermissions("vrs:lbimgs:lbimgs")
	String Lbimgs(){
	    return "vrs/lbimgs/lbimgs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:lbimgs:lbimgs")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LbimgsDO> lbimgsList = lbimgsService.list(query);
		int total = lbimgsService.count(query);
		PageUtils pageUtils = new PageUtils(lbimgsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:lbimgs:add")
	String add(){
	    return "vrs/lbimgs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:lbimgs:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LbimgsDO lbimgs = lbimgsService.get(id);
		model.addAttribute("lbimgs", lbimgs);
	    return "vrs/lbimgs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:lbimgs:add")
	public R save( LbimgsDO lbimgs){
		if(lbimgsService.save(lbimgs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:lbimgs:edit")
	public R update( LbimgsDO lbimgs){
		lbimgsService.update(lbimgs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:lbimgs:remove")
	public R remove( Integer id){
		if(lbimgsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:lbimgs:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		lbimgsService.batchRemove(ids);
		return R.ok();
	}
	
}
