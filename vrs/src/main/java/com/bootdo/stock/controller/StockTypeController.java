package com.bootdo.stock.controller;

import java.util.ArrayList;
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

import com.bootdo.stock.domain.StockTypeDO;
import com.bootdo.stock.service.StockTypeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 库存管理 - 商品分类表
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
 
@Controller
@RequestMapping("/stock/stockType")
public class StockTypeController {
	@Autowired
	private StockTypeService stockTypeService;
	
	@GetMapping()
	@RequiresPermissions("stock:stockType:stockType")
	String StockType(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
	    return "stock/stockType/stockType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:stockType:stockType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StockTypeDO> stockTypeList = stockTypeService.list(query);
		int total = stockTypeService.count(query);
		PageUtils pageUtils = new PageUtils(stockTypeList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/queryByPid")
	@RequiresPermissions("stock:stockType:stockType")
	public List<StockTypeDO> queryByPid(Integer pid){
		List<StockTypeDO> stockTypeList = stockTypeService.queryByPid(pid);
		return stockTypeList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:stockType:add")
	String add(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
		
	    return "stock/stockType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:stockType:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		StockTypeDO stockType = stockTypeService.get(id);
		model.addAttribute("stockType", stockType);
		
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
		
		Integer level = stockType.getLevel();
		if( level.equals(1) ) {//该类型是一级类型
			model.addAttribute("firstPid", "");
			model.addAttribute("secondPid", "");
			model.addAttribute("second", new ArrayList<StockTypeDO>());
		} else if( level.equals(2) ) {
			model.addAttribute("firstPid", stockType.getPid());
			model.addAttribute("secondPid", "");
			model.addAttribute("second", new ArrayList<StockTypeDO>());
		} else if( level.equals(3) ) {
			model.addAttribute("secondPid", stockType.getPid());
			StockTypeDO firstType = stockTypeService.get(stockType.getPid());
			model.addAttribute("firstPid", firstType.getPid());
			
			List<StockTypeDO> second = stockTypeService.queryByPid(firstType.getPid());
			model.addAttribute("second", second);
		}
		
		
	    return "stock/stockType/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:stockType:add")
	public R save( StockTypeDO stockType){
		if(stockTypeService.save(stockType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:stockType:edit")
	public R update( StockTypeDO stockType){
		stockTypeService.update(stockType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:stockType:remove")
	public R remove( Integer id){
		if(stockTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:stockType:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		stockTypeService.batchRemove(ids);
		return R.ok();
	}
	
}
