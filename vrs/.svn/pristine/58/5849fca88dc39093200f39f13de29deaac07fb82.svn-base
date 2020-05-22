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

import com.bootdo.vr.domain.ProductImgsDO;
import com.bootdo.vr.service.ProductImgsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 17:21:44
 */
 
@Controller
@RequestMapping("/vr/productImgs")
public class ProductImgsController {
	@Autowired
	private ProductImgsService productImgsService;
	
	@GetMapping()
	@RequiresPermissions("vr:productImgs:productImgs")
	String ProductImgs(){
	    return "vr/productImgs/productImgs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:productImgs:productImgs")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductImgsDO> productImgsList = productImgsService.list(query);
		int total = productImgsService.count(query);
		PageUtils pageUtils = new PageUtils(productImgsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:productImgs:add")
	String add(){
	    return "vr/productImgs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:productImgs:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProductImgsDO productImgs = productImgsService.get(id);
		model.addAttribute("productImgs", productImgs);
	    return "vr/productImgs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:productImgs:add")
	public R save( ProductImgsDO productImgs){
		if(productImgsService.save(productImgs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:productImgs:edit")
	public R update( ProductImgsDO productImgs){
		productImgsService.update(productImgs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:productImgs:remove")
	public R remove( Integer id){
		if(productImgsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:productImgs:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		productImgsService.batchRemove(ids);
		return R.ok();
	}
	
}
