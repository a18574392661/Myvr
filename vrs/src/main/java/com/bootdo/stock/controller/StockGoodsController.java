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

import com.bootdo.stock.domain.StockGoodsDO;
import com.bootdo.stock.domain.StockTypeDO;
import com.bootdo.stock.service.StockGoodsService;
import com.bootdo.stock.service.StockTypeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 库存管理 - 商品库
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
 
@Controller
@RequestMapping("/stock/stockGoods")
public class StockGoodsController {
	@Autowired
	private StockGoodsService stockGoodsService;
	@Autowired
	private StockTypeService stockTypeService;
	
	@GetMapping()
	@RequiresPermissions("stock:stockGoods:stockGoods")
	String StockGoods(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
	    return "stock/stockGoods/stockGoods";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:stockGoods:stockGoods")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StockGoodsDO> stockGoodsList = stockGoodsService.list(query);
		int total = stockGoodsService.count(query);
		PageUtils pageUtils = new PageUtils(stockGoodsList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/queryByType")
	@RequiresPermissions("stock:stockGoods:stockGoods")
	public List<StockGoodsDO> queryByType(Integer typeId){
		List<StockGoodsDO> stockGoodsList = stockGoodsService.queryByType(typeId);
		return stockGoodsList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:stockGoods:add")
	String add(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
	    return "stock/stockGoods/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:stockGoods:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		StockGoodsDO stockGoods = stockGoodsService.get(id);
		model.addAttribute("stockGoods", stockGoods);
		
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
		
		StockTypeDO stockType = stockTypeService.get(stockGoods.getTypeId());
		
		Integer level = stockType.getLevel();
		if( level.equals(1) ) {//该类型是一级分类下的商品
			model.addAttribute("firstPid", stockType.getId());
			model.addAttribute("secondPid", "");
			model.addAttribute("thirdPid", "");
			model.addAttribute("second", new ArrayList<StockTypeDO>());
			model.addAttribute("third", new ArrayList<StockTypeDO>());
		} else if( level.equals(2) ) {//二级分类下的商品
			model.addAttribute("firstPid", stockType.getPid());
			model.addAttribute("secondPid", stockType.getId());
			
			model.addAttribute("thirdPid", "");
			List<StockTypeDO> second = stockTypeService.queryByPid(stockType.getPid());
			model.addAttribute("second", second);
			
			model.addAttribute("third", new ArrayList<StockTypeDO>());
		} else if( level.equals(3) ) {
			model.addAttribute("thirdPid", stockType.getId());
			model.addAttribute("secondPid", stockType.getPid());
			
			StockTypeDO firstType = stockTypeService.get(stockType.getPid());
			model.addAttribute("firstPid", firstType.getPid());
			
			List<StockTypeDO> second = stockTypeService.queryByPid(firstType.getPid());
			model.addAttribute("second", second);
			
			List<StockTypeDO> third = stockTypeService.queryByPid(stockType.getPid());
			model.addAttribute("third", third);
		}
		
	    return "stock/stockGoods/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:stockGoods:add")
	public R save( StockGoodsDO stockGoods){
		if(stockGoodsService.save(stockGoods)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:stockGoods:edit")
	public R update( StockGoodsDO stockGoods){
		stockGoodsService.update(stockGoods);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:stockGoods:remove")
	public R remove( Integer id){
		if(stockGoodsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:stockGoods:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		stockGoodsService.batchRemove(ids);
		return R.ok();
	}
	
}
