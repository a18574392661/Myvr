package com.bootdo.stock.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.stock.domain.StockGoodsDO;
import com.bootdo.stock.domain.StockInDO;
import com.bootdo.stock.domain.StockTypeDO;
import com.bootdo.stock.service.StockGoodsService;
import com.bootdo.stock.service.StockInService;
import com.bootdo.stock.service.StockTypeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 库存管理 - 商品入库表
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
 
@Controller
@RequestMapping("/stock/stockIn")
public class StockInController {
	@Autowired
	private StockInService stockInService;
	
	@Autowired
	private StockGoodsService stockGoodsService;
	@Autowired
	private StockTypeService stockTypeService;
	
	@GetMapping()
	@RequiresPermissions("stock:stockIn:stockIn")
	String StockIn(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
	    return "stock/stockIn/stockIn";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:stockIn:stockIn")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StockInDO> stockInList = stockInService.list(query);
		int total = stockInService.count(query);
		PageUtils pageUtils = new PageUtils(stockInList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:stockIn:add")
	String add(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
	    return "stock/stockIn/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:stockIn:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		StockInDO stockIn = stockInService.get(id);
		model.addAttribute("stockIn", stockIn);
	    return "stock/stockIn/edit";
	}
	
	/**
	 * 保存
	 */
	@Transactional
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:stockIn:add")
	public R save( StockInDO stockIn){
		if( stockIn.getCreateTime() == null ) {
			stockIn.setCreateTime(new Date());
		}
		
		if(stockInService.save(stockIn)>0){
			StockGoodsDO stockGoodsDO = new StockGoodsDO();
			stockGoodsDO.setId(stockIn.getGoodsId());
			stockGoodsDO.setStock(stockIn.getNum());
			//修改商品库库存
			stockGoodsService.goodsIn(stockGoodsDO);
			
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:stockIn:edit")
	public R update( StockInDO stockIn){
		stockInService.update(stockIn);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Transactional
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:stockIn:remove")
	public R remove( Integer id){
		
		StockInDO stockIn = stockInService.get(id);
		StockGoodsDO stockGoodsDO = new StockGoodsDO();
		stockGoodsDO.setId(stockIn.getGoodsId());
		stockGoodsDO.setStock(stockIn.getNum());
		//修改商品库库存
		stockGoodsService.goodsOut(stockGoodsDO);
		
		if(stockInService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:stockIn:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		stockInService.batchRemove(ids);
		return R.ok();
	}
	
}
