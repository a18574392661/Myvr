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

import com.bootdo.vr.domain.ProductLeftStyleDO;
import com.bootdo.vr.service.ProductLeftStyleService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 产品套餐中的小风格全景分类图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-28 15:59:55
 */
 
@Controller
@RequestMapping("/vr/productLeftStyle")
public class ProductLeftStyleController {
	@Autowired
	private ProductLeftStyleService productLeftStyleService;
	
	@GetMapping()
	@RequiresPermissions("vr:productLeftStyle:productLeftStyle")
	String ProductLeftStyle(){
	    return "vr/productLeftStyle/productLeftStyle";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:productLeftStyle:productLeftStyle")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductLeftStyleDO> productLeftStyleList = productLeftStyleService.list(query);
		int total = productLeftStyleService.count(query);
		PageUtils pageUtils = new PageUtils(productLeftStyleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:productLeftStyle:add")
	String add(){
	    return "vr/productLeftStyle/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:productLeftStyle:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProductLeftStyleDO productLeftStyle = productLeftStyleService.get(id);
		model.addAttribute("productLeftStyle", productLeftStyle);
	    return "vr/productLeftStyle/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:productLeftStyle:add")
	public R save( ProductLeftStyleDO productLeftStyle){
		if(productLeftStyleService.save(productLeftStyle)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:productLeftStyle:edit")
	public R update( ProductLeftStyleDO productLeftStyle){
		productLeftStyleService.update(productLeftStyle);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:productLeftStyle:remove")
	public R remove( Integer id){
		if(productLeftStyleService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:productLeftStyle:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		productLeftStyleService.batchRemove(ids);
		return R.ok();
	}
	
}
