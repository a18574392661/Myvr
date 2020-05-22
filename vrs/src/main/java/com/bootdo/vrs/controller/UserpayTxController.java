package com.bootdo.vrs.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.domain.UserpayTxDO;
import com.bootdo.vrs.service.UserpayTxService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 提现记录表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-26 13:23:43
 */
 
@Controller
@RequestMapping("/vrs/userpayTx")
public class UserpayTxController extends BaseController {
	@Autowired
	private UserpayTxService userpayTxService;
	
	@GetMapping()
	@RequiresPermissions("vrs:userpayTx:userpayTx")
	String UserpayTx(){
	    return "vrs/userpayTx/userpayTx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:userpayTx:userpayTx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (getUserId()!=1&&getUserId()!=138){
			params.put("pid",getUserId());
		}

        Query query = new Query(params);
		List<UserpayTxDO> userpayTxList = userpayTxService.list(query);
		int total = userpayTxService.count(query);
		PageUtils pageUtils = new PageUtils(userpayTxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:userpayTx:add")
	String add(){
	    return "vrs/userpayTx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:userpayTx:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UserpayTxDO userpayTx = userpayTxService.get(id);
		model.addAttribute("userpayTx", userpayTx);
	    return "vrs/userpayTx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:userpayTx:add")
	public R save( UserpayTxDO userpayTx){
		if(userpayTxService.save(userpayTx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:userpayTx:edit")
	public R update( UserpayTxDO userpayTx){
		userpayTxService.update(userpayTx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:userpayTx:remove")
	public R remove( Long id){
		if(userpayTxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:userpayTx:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		userpayTxService.batchRemove(ids);
		return R.ok();
	}
	
}
