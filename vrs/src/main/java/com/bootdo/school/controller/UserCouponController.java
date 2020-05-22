package com.bootdo.school.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.school.dao.UserCouponDao;
import com.bootdo.school.domain.UserCouponDO;
import com.bootdo.school.service.UserCouponService;
import com.bootdo.system.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-08 13:58:02
 */
 
@Controller
@RequestMapping("/school/userCoupon")
public class UserCouponController {
	@Autowired
	private UserCouponService userCouponService;

	@Autowired
	private UserService userService;

	@Autowired
	private RedissonClient redissonClient;


	@Autowired
	private UserCouponDao userCouponDao;
	
	@GetMapping()
	@RequiresPermissions("school:userCoupon:userCoupon")
	String UserCoupon(){
	    return "school/userCoupon/userCoupon";
	}



	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:userCoupon:userCoupon")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserCouponDO> userCouponList = userCouponService.list(query);
		int total = userCouponService.count(query);
		PageUtils pageUtils = new PageUtils(userCouponList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:userCoupon:add")
	String add(ModelMap map){

		Map map1=new HashedMap();
		map1.put("rid",62);
	    map.put("stus",userService.list(map1));

		return "school/userCoupon/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:userCoupon:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UserCouponDO userCoupon = userCouponService.get(id);
		model.addAttribute("userCoupon", userCoupon);
	    return "school/userCoupon/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:userCoupon:add")
	public R save( UserCouponDO userCoupon){



		//直接写了
		RLock lock = redissonClient.getLock("lock");
		try {
			boolean b = lock.tryLock(10, TimeUnit.SECONDS);
			if (b==true){
				//先查询有没有
			int c=userCouponDao.queryUidDisbles(userCoupon.getUid()+"");
			if (c>0){
				return  R.error("该学员已经添加过优惠券！");
			}


				//添加
				if(userCouponService.save(userCoupon)>0){

					return R.ok();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}



		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:userCoupon:edit")
	public R update( UserCouponDO userCoupon){
		userCoupon.setUpdatedate(new Date());
		userCouponService.update(userCoupon);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:userCoupon:remove")
	public R remove( Long id){
		if(userCouponService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:userCoupon:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		userCouponService.batchRemove(ids);
		return R.ok();
	}
	
}
