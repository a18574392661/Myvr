package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.domain.PayvipDO;
import com.bootdo.vrs.service.PayvipService;
import com.bootdo.vrs.util.UserVerificationUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-25 16:50:07
 */
 
@Controller
@RequestMapping("/vrs/payvip")
public class PayvipController {
	@Autowired
	private PayvipService payvipService;



	@Autowired
	UserVerificationUtil userVerificationUtil;

	@RequestMapping("/test")
	@ResponseBody
	public  void d(){
		//UserVerificationUtil userVerificationUtil=new UserVerificationUtil();
		userVerificationUtil.setUserPrice(161l,new BigDecimal(1000),"开通会员",null);

	}
	
	@GetMapping()
	@RequiresPermissions("vrs:payvip:payvip")
	String Payvip(){
	    return "vrs/payvip/payvip";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:payvip:payvip")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("vipid", MessageConstantVrs.GET_VIPID);
        Query query = new Query(params);

		List<PayvipDO> payvipList = payvipService.list(query);
		int total = payvipService.count(query);
		PageUtils pageUtils = new PageUtils(payvipList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:payvip:add")
	String add(){
	    return "vrs/payvip/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:payvip:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PayvipDO payvip = payvipService.get(id);
		model.addAttribute("payvip", payvip);
	    return "vrs/payvip/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:payvip:add")
	public R save( PayvipDO payvip){
		payvip.setVipid(Integer.parseInt(MessageConstantVrs.GET_VIPID));
		if(payvipService.save(payvip)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:payvip:edit")
	public R update( PayvipDO payvip){
		payvipService.update(payvip);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:payvip:remove")
	public R remove( Integer id){
		if(payvipService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:payvip:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		payvipService.batchRemove(ids);
		return R.ok();
	}
	
}
