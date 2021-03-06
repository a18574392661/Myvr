package com.bootdo.vrs.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.UserImgclsDao;
import com.bootdo.vrs.dao.UsertAllotDao;
import com.bootdo.vrs.domain.PaydetailesDO;
import com.bootdo.vrs.domain.UserDO;
import com.bootdo.vrs.domain.UserImgclsDO;
import com.bootdo.vrs.service.PaydetailesService;
import com.bootdo.vrs.service.UserAllotService;
import com.bootdo.vrs.service.UserImgclsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 加入我的云视
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-22 19:52:53
 */
 
@Controller
@RequestMapping("/vrs/userImgcls")
public class UserImgclsController  extends BaseController {
	@Autowired
	private UserImgclsService userImgclsService;

	@Autowired
	private UserAllotService userAllotService;

	@Autowired
	private  UsertAllotDao usertAllotDao;


	@Autowired
	private PaydetailesService paydetailesService;

	@Autowired
	private UserImgclsDao imgclsDao;


	@Autowired
	private UserAllotService userService;


	@GetMapping()
	@RequiresPermissions("vrs:userImgcls:userImgcls")
	String UserImgcls(ModelMap map)
	{
		List<UserDO> userList=userService.list(null);
		map.put("ulist",userList);

	    return "vrs/userImgcls/userImgcls";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:userImgcls:userImgcls")
	public PageUtils list(@RequestParam Map<String, Object> params){

		//判断是不是vr管理员
		if (getUserId()!=1&&getUserId()!=138){
			//不是2个管理员
			params.put("uid",getUserId());
		}



		//查询列表数据
        Query query = new Query(params);
		List<UserImgclsDO> userImgclsList = userImgclsService.list(query);
		int total = userImgclsService.count(query);
		PageUtils pageUtils = new PageUtils(userImgclsList, total);
		return pageUtils;
	}


	@ResponseBody
	@GetMapping("/list2")
	@RequiresPermissions("vrs:userImgcls:userImgcls")
	public PageUtils list2(@RequestParam Map<String, Object> params){

		//查询列表数据
		Query query = new Query(params);
		List<UserImgclsDO> userImgclsList = userImgclsService.list(query);
		int total = userImgclsService.count(query);
		PageUtils pageUtils = new PageUtils(userImgclsList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("vrs:userImgcls:add")
	String add(){
	    return "vrs/userImgcls/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:userImgcls:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UserImgclsDO userImgcls = userImgclsService.get(id);
		model.addAttribute("userImgcls", userImgcls);
	    return "vrs/userImgcls/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:userImgcls:add")
	public R save( UserImgclsDO userImgcls){
		if(userImgclsService.save(userImgcls)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:userImgcls:edit")
	public R update( UserImgclsDO userImgcls){
		userImgclsService.update(userImgcls);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:userImgcls:remove")
	public R remove( Integer id){


		if(userImgclsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchUser")
	@ResponseBody
	//@RequiresPermissions("vrs:userImgcls:batchRemove")
	public R batchUser(String ids,String proid){
		System.out.println(ids+"//"+proid);
		try {
			userImgclsService.batchUser(ids,proid);
		}
		catch (RuntimeException e){
				e.printStackTrace();
				return R.error(e.getMessage());
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return R.ok("分配成功!");
	}


	@GetMapping("/to_saveMyUserName")
	public  String to_saveMyUserName(ModelMap map, String uid, String cid){
   			map.put("uid",uid);
   			map.put("cid",cid);
		//查询非会员可以
		PaydetailesDO paydetailesDO=paydetailesService.get(Integer.parseInt(MessageConstantVrs.GET_VIPID));
		int count=paydetailesDO.getCount()!=null?paydetailesDO.getCount():10;
		map.put("count",count);
		return  "vrs/userImgcls/myuser";
	}

	@GetMapping("/to_showMyUserName")
	public  String to_showMyUserName(ModelMap map, String uid, String cid){
		map.put("uid",uid);
		map.put("cid",cid);
		//查询非会员可以
		PaydetailesDO paydetailesDO=paydetailesService.get(Integer.parseInt(MessageConstantVrs.GET_VIPID));
		int count=paydetailesDO.getCount()!=null?paydetailesDO.getCount():10;
		map.put("count",count);
		return  "vrs/userImgcls/showuser";
	}



	@RequestMapping("/saveMyUserName")
	@ResponseBody
	public  PageUtils saveMyUserName(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<UserDO> userImgclsList = userAllotService.saveMyUserName(query);
		int total = usertAllotDao.saveMyUserNamecount(query);
		PageUtils pageUtils = new PageUtils(userImgclsList, total);
		return pageUtils;
	}


	@RequestMapping("/removeUserImg")
	@ResponseBody
	public  R removeUserImg(String uid,String cid){
		try {
			imgclsDao.removeUserImg(uid,cid);
		}
		catch (Exception e){
		e.printStackTrace();
			return R.error();
		}



		return R.ok();

	}


	//查看我的图库 分配给子账号
	@RequestMapping("/showMyimgs")
	public  String showMyimgs(){


		return "vrs/userImgcls/myImgUser";
	}

	@RequestMapping("/myImg")
	@ResponseBody
	public  List<UserImgclsDO>  getUserImg(){

		String uid="";
		if (getUserId()!=1&&getUserId()!=138){
			uid=getUserId()+"";
		}

		List<UserImgclsDO> userImgclsDOS=imgclsDao.getUserImg(uid);


		return userImgclsDOS;
	}


	@RequestMapping("/to_user")
	public  String to_user(String ids,ModelMap map){
		if (StringUtils.isBlank(ids)||"null".equals(ids)){
			//当前用户的图库
			return "redirect:/vrs/userImgcls/showMyimgs";
		}

		map.put("ids",ids);
		//一次性选择用户界面我
		return  "vrs/userImgcls/toUser";
	}

	@ResponseBody
	@RequestMapping("/touser")
	public List<UserDO>   touser(){
		String uid=null;
		if (getUserId()!=1&&getUserId()!=138){
			uid=getUserId()+"";
		}
		List<UserDO> list=usertAllotDao.getChildUser(uid);
		return list;
	}




	//确定批量分配图片
	@RequestMapping("/fpuserImg")
	@ResponseBody
	public  R fpuserImg(String imgs,@RequestParam("uids") String uids){
		if (StringUtils.isBlank(uids)){
			return  R.error("请选择需要分配的用户");
		}
		try {
      		String[] ims=imgs.split(",");
      		String[] us=uids.split(",");

			userImgclsService.fpuserImg(ims,us);
			return R.ok("分配成功!");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return R.error("分配失败");

	}




}
