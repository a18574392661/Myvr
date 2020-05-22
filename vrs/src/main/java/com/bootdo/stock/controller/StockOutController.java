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
import com.bootdo.stock.domain.StockOutDO;
import com.bootdo.stock.domain.StockTypeDO;
import com.bootdo.stock.service.StockGoodsService;
import com.bootdo.stock.service.StockOutService;
import com.bootdo.stock.service.StockTypeService;
import com.alibaba.druid.util.StringUtils;
import com.bootdo.common.utils.OrderCcodeUtils;
import com.bootdo.common.utils.OrderCodeUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 库存管理 - 商品出库(卖出)表
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
 
@Controller
@RequestMapping("/stock/stockOut")
public class StockOutController {
	@Autowired
	private StockOutService stockOutService;
	@Autowired
	private StockGoodsService stockGoodsService;
	@Autowired
	private StockTypeService stockTypeService;
	
	@GetMapping()
	@RequiresPermissions("stock:stockOut:stockOut")
	String StockOut(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
	    return "stock/stockOut/stockOut";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:stockOut:stockOut")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StockOutDO> stockOutList = stockOutService.list(query);
		int total = stockOutService.count(query);
		PageUtils pageUtils = new PageUtils(stockOutList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/createOrdercode")
	@RequiresPermissions("stock:stockOut:add")
	String createOrdercode(){
	    return OrderCodeUtils.getTenNumberCode();
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:stockOut:add")
	String add(String ordercode, Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
		
		if( StringUtils.isEmpty(ordercode) ) {
			model.addAttribute("isNew", true);
		} else {
			model.addAttribute("isNew", false);
			model.addAttribute("ordercode", ordercode);
		}
		
	    return "stock/stockOut/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:stockOut:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		StockOutDO stockOut = stockOutService.get(id);
		model.addAttribute("stockOut", stockOut);
	    return "stock/stockOut/edit";
	}
	
	/**
	 * 保存
	 */
	@Transactional
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:stockOut:add")
	public R save( StockOutDO stockOut){
		
		if( stockOut.getCreateTime() == null ) {
			stockOut.setCreateTime(new Date());
		}
		
		if(stockOutService.save(stockOut)>0){
			
			StockGoodsDO stockGoodsDO = new StockGoodsDO();
			stockGoodsDO.setId(stockOut.getGoodsId());
			stockGoodsDO.setStock(stockOut.getNum());
			//修改商品库库存
			stockGoodsService.goodsOut(stockGoodsDO);
			
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:stockOut:edit")
	public R update( StockOutDO stockOut){
		stockOutService.update(stockOut);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:stockOut:remove")
	public R remove( Integer id){
		
		StockOutDO stockOut = stockOutService.get(id);
		StockGoodsDO stockGoodsDO = new StockGoodsDO();
		stockGoodsDO.setId(stockOut.getGoodsId());
		stockGoodsDO.setStock(stockOut.getNum());
		//修改商品库库存
		stockGoodsService.goodsIn(stockGoodsDO);
		
		if(stockOutService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:stockOut:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		stockOutService.batchRemove(ids);
		return R.ok();
	}
	
}
