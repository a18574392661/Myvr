package com.bootdo.vr.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.OrderCodeUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShareCodeUtil;
import com.bootdo.vr.common.VrContant;
import com.bootdo.vr.domain.ProductActiveCodeDO;
import com.bootdo.vr.service.ProductActiveCodeService;

/**
 * 产品套餐激活码表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-28 19:05:06
 */
 
@Controller
@RequestMapping("/vr/productActiveCode")
public class ProductActiveCodeController {
	@Autowired
	private ProductActiveCodeService productActiveCodeService;
	
	@GetMapping()
	@RequiresPermissions("vr:topStyle:topStyle")
	String ProductActiveCode(){
	    return "vr/productActiveCode/productActiveCode";
	}
	
	@ResponseBody
	@GetMapping("/list/{productId}")
	@RequiresPermissions("vr:topStyle:topStyle")
	public PageUtils list(@PathVariable("productId") Integer productId, @RequestParam Map<String, Object> params){
		params.put("productId", productId);
		//查询列表数据
        Query query = new Query(params);
		List<ProductActiveCodeDO> productActiveCodeList = productActiveCodeService.list(query);
		int total = productActiveCodeService.count(query);
		PageUtils pageUtils = new PageUtils(productActiveCodeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:topStyle:add")
	String add(){
	    return "vr/productActiveCode/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:topStyle:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProductActiveCodeDO productActiveCode = productActiveCodeService.get(id);
		model.addAttribute("productActiveCode", productActiveCode);
	    return "vr/productActiveCode/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:topStyle:add")
	public R save( ProductActiveCodeDO productActiveCode){
		if(productActiveCodeService.save(productActiveCode)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:topStyle:edit")
	public R update( ProductActiveCodeDO productActiveCode){
		productActiveCodeService.update(productActiveCode);
		return R.ok();
	}
	
	@Transactional
	@PostMapping( "/buildActiveCode")
	@ResponseBody
	@RequiresPermissions("vr:topStyle:add")
	public R buildActiveCode(Integer productId) {
		
		if( productId == null || productId <= 0 ) {
			return R.error("参数错误");
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		params.put("createTime", new Date());
		params.put("status", 0);//默认未使用
		
		List<String> codeList = new ArrayList<String>();
		
		for( int i = 0; i < VrContant.build_active_code_number; i++ ) {
			codeList.add(OrderCodeUtils.getTenNumberCode());//根据当前时间生成邀请码
		}
		params.put("codes", codeList);
		
		if( productActiveCodeService.saveBatch(params) == VrContant.build_active_code_number ) {
			return R.ok();
		}
		
		return R.error("生成失败，请重试");
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:topStyle:remove")
	public R remove( Integer id){
		if(productActiveCodeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:topStyle:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		productActiveCodeService.batchRemove(ids);
		return R.ok();
	}
	
}
