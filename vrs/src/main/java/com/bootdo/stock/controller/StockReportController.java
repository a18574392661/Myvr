package com.bootdo.stock.controller;

import java.util.Date;
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

import com.bootdo.stock.domain.StockInDO;
import com.bootdo.stock.domain.StockOutDO;
import com.bootdo.stock.domain.StockTypeDO;
import com.bootdo.stock.service.StockGoodsService;
import com.bootdo.stock.service.StockInService;
import com.bootdo.stock.service.StockOutService;
import com.bootdo.stock.service.StockTypeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 日志报表
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
 
@Controller
@RequestMapping("/stock/report")
public class StockReportController {
	@Autowired
	private StockInService stockInService;
	@Autowired
	private StockOutService stockOutService;
	@Autowired
	private StockTypeService stockTypeService;
	
	@GetMapping("/in")
	@RequiresPermissions("stock:report:report")
	String in(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
	    return "stock/report/reportIn";
	}
	
	@ResponseBody
	@GetMapping("/inList")
	@RequiresPermissions("stock:report:report")
	public PageUtils inList(@RequestParam Map<String, Object> params){
		//查询列表数据
        //Query query = new Query(params);
		List<StockInDO> stockInList = stockInService.list(params);
		//int total = stockInService.count(params);
		PageUtils pageUtils = new PageUtils(stockInList, 0);
		return pageUtils;
	}
	
	@GetMapping("/out")
	@RequiresPermissions("stock:report:report")
	String out(Model model){
		List<StockTypeDO> first = stockTypeService.queryFirstLevel();
		model.addAttribute("first", first);
	    return "stock/report/reportOut";
	}
	
	@ResponseBody
	@GetMapping("/outList")
	@RequiresPermissions("stock:report:report")
	public PageUtils outList(@RequestParam Map<String, Object> params){
        //Query query = new Query(params);
		List<StockOutDO> stockOutList = stockOutService.list(params);
		//int total = stockOutService.count(query);
		PageUtils pageUtils = new PageUtils(stockOutList, 0);
		return pageUtils;
	}
	
}
