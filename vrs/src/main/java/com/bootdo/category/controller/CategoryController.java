package com.bootdo.category.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.category.domain.CategoryDO;
import com.bootdo.category.service.CategoryService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-11 19:51:02
 */
 
@Controller
@RequestMapping("/category/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	@RequiresPermissions("category:category:category")
	String Category(){
	    return "category/category/category";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("category:category:category")
	public PageUtils list(@RequestParam(required=false) Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CategoryDO> categoryList = categoryService.list(query);
		int total = categoryService.count(query);
		PageUtils pageUtils = new PageUtils(categoryList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/listall")
	@RequiresPermissions("category:category:category")
	public List<CategoryDO> list(){
		//查询列表数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1);
		List<CategoryDO> categoryList = categoryService.list(map);
	
		return categoryList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("category:category:add")
	String add(){
	    return "category/category/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("category:category:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CategoryDO category = categoryService.get(id);
		model.addAttribute("category", category);
	    return "category/category/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("category:category:add")
	public R save( CategoryDO category){
		
		category.setState("1");
		if(categoryService.save(category)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("category:category:edit")
	public R update( CategoryDO category){
		categoryService.update(category);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("category:category:remove")
	public R remove( Integer id){
		if(categoryService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("category:category:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		categoryService.batchRemove(ids);
		return R.ok();
	}
	
}
