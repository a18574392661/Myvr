package com.bootdo.school.controller;

import com.bootdo.common.utils.*;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-15 11:51:47
 */
 
@Controller
@RequestMapping("/school/user")
public class StuController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;


	@GetMapping()
	@RequiresPermissions("school:user:user")
	String User(){
	    return "school/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据

        Query query = new Query(params);
		List<UserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:user:add")
	String add(){
	    return "school/user/add";
	}

	@GetMapping("/edit/{userId}")
	@RequiresPermissions("school:user:edit")
	String edit(@PathVariable("userId") Long userId,Model model){
		UserDO user = userService.get(userId);
		model.addAttribute("user", user);
	    return "school/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:user:add")
	public R save(UserDO user,String type){
		if (StringUtils.isNotBlank(type)){
			//学员页面调过来的
			user.setStatus(1);
			user.setPassword(MD5Utils.encrypt(user.getUsername(),getTelPwd(user.getUsername())));
		}

		Map map=new HashedMap();
		map.put("username",user.getUsername());
		//先查询用户名是否重复
		int count=userDao.queryUserNameDisble(map);
		if (count>0){
			return R.error(MessageConstant.USSR_DISERBL);
		}
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:user:edit")
	public R update( UserDO user){

		Map map=new HashedMap();
		map.put("username",user.getUsername());
		map.put("uid",user.getUserId());
		//先查询用户名是否重复
		int count=userDao.queryUserNameDisble(map);
		if (count>0){
			return R.error(MessageConstant.USSR_DISERBL);
		}
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:user:remove")
	public R remove( Long userId){
		if(userService.remove(userId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:user:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] userIds){
		userService.batchremove(userIds);

		return R.ok();
	}

	public  static   String getTelPwd(String tel){
		if (StringUtils.isBlank(tel)){

			return tel;
		}
		tel=tel.substring(5);
		System.out.println(tel);
		return  tel;


	}

	public static void main(String[] args) {

		getTelPwd("15573314821");
	}
	
}
