package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.PayvipDao;
import com.bootdo.vrs.domain.PaydetailesDO;
import com.bootdo.vrs.domain.PayvipDO;
import com.bootdo.vrs.service.PaydetailesService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 支付介绍详情价格表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 10:19:09
 */
 
@Controller
@RequestMapping("/vrs/paydetailes")
public class PaydetailesController {
	@Autowired
	private PaydetailesService paydetailesService;

	@Autowired
	private PayvipDao payvipDao;

	
	@GetMapping()
	@RequiresPermissions("vrs:paydetailes:paydetailes")
	String Paydetailes(){
	    return "vrs/paydetailes/paydetailes";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:paydetailes:paydetailes")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PaydetailesDO> paydetailesList = paydetailesService.list(query);
		int total = paydetailesService.count(query);
		PageUtils pageUtils = new PageUtils(paydetailesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:paydetailes:add")
	String add(){
	    return "vrs/paydetailes/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:paydetailes:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PaydetailesDO paydetailes = paydetailesService.get(id);
		//查询月份对应价格
		Map<String,Object> map=new HashedMap();
		map.put("vipid", MessageConstantVrs.GET_VIPID);
		List<PayvipDO> list=payvipDao.list(map);
		for (PayvipDO payvipDO : list) {
			if (payvipDO.getStatus()==1){
				payvipDO.setChecked(true);
			}
		}
		model.addAttribute("viplist", list);
		model.addAttribute("paydetailes", paydetailes);
	    return "vrs/paydetailes/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:paydetailes:add")
	public R save( PaydetailesDO paydetailes){
		if(paydetailesService.save(paydetailes)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:paydetailes:edit")
	public R update( PaydetailesDO paydetailes){

		paydetailesService.update(paydetailes);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:paydetailes:remove")
	public R remove( Integer id){

		return R.error("系统设置不能删除!");

		/*if(paydetailesService.remove(id)>0){
			//return R.ok();
		}*/
		//return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:paydetailes:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		paydetailesService.batchRemove(ids);
		return R.ok();
	}
	
}
