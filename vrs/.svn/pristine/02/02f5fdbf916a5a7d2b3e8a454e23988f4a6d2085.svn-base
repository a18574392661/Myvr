package com.bootdo.vrs.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.domain.UserDO;
import com.bootdo.vrs.domain.UserImgclsDO;
import com.bootdo.vrs.domain.UserShopDO;
import com.bootdo.vrs.service.UserAllotService;
import com.bootdo.vrs.service.UserImgclsService;
import com.bootdo.vrs.service.UserShopService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户加入图片购物车表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-21 22:19:09
 */
 
@Controller
@RequestMapping("/vrs/userShop")
public class UserShopController extends BaseController {
	@Autowired
	private UserShopService userShopService;

	@Autowired
	private UserImgclsService userImgclsService;

	@Autowired
	private UserAllotService userService;

	
	@GetMapping()
	@RequiresPermissions("vrs:userShop:userShop")
	String UserShop(ModelMap map){

		//查询所有vr用户
		List<UserDO> userList=userService.list(null);
		map.put("ulist",userList);
		return "vrs/userShop/userShop";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:userShop:userShop")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		//先这样写死 可以优化1
		if (getUserId()!=1&&getUserId()!=138){
			params.put("uid",getUserId());
		}


        Query query = new Query(params);
		List<UserShopDO> userShopList = userShopService.list(query);
		int total = userShopService.count(query);
		PageUtils pageUtils = new PageUtils(userShopList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:userShop:add")
	String add(){
	    return "vrs/userShop/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:userShop:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UserShopDO userShop = userShopService.get(id);
		model.addAttribute("userShop", userShop);
	    return "vrs/userShop/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:userShop:add")
	public R save( UserShopDO userShop){
		if(userShopService.save(userShop)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:userShop:edit")
	public R update( UserShopDO userShop){
		userShopService.update(userShop);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:userShop:remove")
	public R remove( Long id){
		if(userShopService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:userShop:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		userShopService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping( "/saveMyPro")
	@ResponseBody
	//@RequiresPermissions("vrs:userShop:batchRemove")
	public R saveMyPro(Long id){
		//先查询是否是会员

		try {
			UserShopDO userShopDO=userShopService.get(id);
			UserImgclsDO userImgclsDO=new UserImgclsDO();
			userImgclsDO.setCid(userShopDO.getProid());
			userImgclsDO.setUid(userShopDO.getUid());
			userImgclsDO.setName(userShopDO.getName());
			userImgclsService.saveMyPro(userImgclsDO);

		}
		catch (RuntimeException e){
			e.printStackTrace();
			return	R.error(e.getMessage());
		}
		catch (Exception e){
			e.printStackTrace();
			return	R.error(MessageConstantVrs.USER_PRO_ERROR);
		}


		return R.ok(MessageConstantVrs.USER_PRO_SUCCESS);
	}


}
