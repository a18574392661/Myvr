package com.bootdo.vr.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.vr.domain.ProductLeftStyleImgsDO;
import com.bootdo.vr.service.ProductLeftStyleImgsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 产中选中的热点图表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 20:10:53
 */
 
@Controller
@RequestMapping("/vr/productLeftStyleImgs")
public class ProductLeftStyleImgsController {
	@Autowired
	private ProductLeftStyleImgsService productLeftStyleImgsService;
	
	@GetMapping()
	@RequiresPermissions("vr:productLeftStyleImgs:productLeftStyleImgs")
	String ProductLeftStyleImgs(){
	    return "vr/productLeftStyleImgs/productLeftStyleImgs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:productLeftStyleImgs:productLeftStyleImgs")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductLeftStyleImgsDO> productLeftStyleImgsList = productLeftStyleImgsService.list(query);
		int total = productLeftStyleImgsService.count(query);
		PageUtils pageUtils = new PageUtils(productLeftStyleImgsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:productLeftStyleImgs:add")
	String add(){
	    return "vr/productLeftStyleImgs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:productLeftStyleImgs:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProductLeftStyleImgsDO productLeftStyleImgs = productLeftStyleImgsService.get(id);
		model.addAttribute("productLeftStyleImgs", productLeftStyleImgs);
	    return "vr/productLeftStyleImgs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:productLeftStyleImgs:add")
	public R save( ProductLeftStyleImgsDO productLeftStyleImgs){
		if(productLeftStyleImgsService.save(productLeftStyleImgs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:productLeftStyleImgs:edit")
	public R update( ProductLeftStyleImgsDO productLeftStyleImgs){
		productLeftStyleImgsService.update(productLeftStyleImgs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:productLeftStyleImgs:remove")
	public R remove( Integer id){
		if(productLeftStyleImgsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:productLeftStyleImgs:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		productLeftStyleImgsService.batchRemove(ids);
		return R.ok();
	}
	
}
