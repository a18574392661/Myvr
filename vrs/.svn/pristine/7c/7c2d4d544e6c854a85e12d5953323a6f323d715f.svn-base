package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.ImgCls2Dao;
import com.bootdo.vrs.domain.ImgCls2DO;
import com.bootdo.vrs.domain.ImgClsDO;
import com.bootdo.vrs.domain.TitleClsDO;
import com.bootdo.vrs.service.ImgCls2Service;
import com.bootdo.vrs.service.TitleClsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 第二个分类表  换色系统
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-14 09:53:32
 */
 
@Controller
@RequestMapping("/vrs/imgCls2")
public class ImgCls2Controller {
	@Autowired
	private ImgCls2Service imgCls2Service;

	@Autowired
	private TitleClsService titleClsService;

	@Autowired
	private ImgCls2Dao imgCls2DaO;


	//获取所属分类
	@ResponseBody
	@GetMapping("/getCategory")
	ImgCls2DO getCategory(Integer id){
		ImgCls2DO imgCls2 = imgCls2Service.getCategory(id);
	    return imgCls2;
	}
	
	@GetMapping()
	@RequiresPermissions("vrs:imgCls2:imgCls2")
	String ImgCls2(){
	    return "vrs/imgCls2/imgCls2";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:imgCls2:imgCls2")
	public PageUtils list(@RequestParam Map<String, Object> params){
		Query query=null;
		if (params!=null&& StringUtils.isNotBlank(params.get("offset")+"")&&params.get("limit")!=null){
			 query = new Query(params);
		}
		
		//查询列表数据
		List<ImgCls2DO> imgCls2List = imgCls2Service.list(query);
		//添加菜单
		Map<Integer,String> menu = new HashMap<Integer, String>();
		for (ImgCls2DO imgCls2DO : imgCls2List) {
			Integer pid = imgCls2DO.getPid();
			if(pid != null && pid == 0) {
				//key = id value = naem
				menu.put(imgCls2DO.getId(), imgCls2DO.getName());
			}
		}
		//添加分类名称
		for (ImgCls2DO imgCls2DO : imgCls2List) {
			Integer pid = imgCls2DO.getPid();
			if(menu.containsKey(pid)) {
				imgCls2DO.setAliasPid(menu.get(pid));
			}
		}
		
		int total = imgCls2Service.count(query);
		PageUtils pageUtils = new PageUtils(imgCls2List, total);
		return pageUtils;
	}
	
	@ResponseBody
	@RequestMapping("/top3")
	public List<ImgCls2DO> top3(@RequestParam Map<String, Object> params){

		List<ImgCls2DO> top3 = imgCls2Service.listTopThree(params);
		
		return top3;
	}
	
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:imgCls2:add")
	String add(Model model){
		//添加显示所属分类--换色系统
		TitleClsDO queryTypeId = titleClsService.queryTypeId(MessageConstantVrs.HUANSEXXITON);
		model.addAttribute("menu", queryTypeId);
		
		return "vrs/imgCls2/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:imgCls2:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ImgCls2DO imgCls2 = imgCls2Service.get(id);
		//所属分类
		TitleClsDO queryTypeId = titleClsService.queryTypeId(MessageConstantVrs.HUANSEXXITON);
		
		//获取所有的一级分类，可以修改上级分类
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("pid", "0");
		List<ImgCls2DO> list = imgCls2Service.list(param);
			
		model.addAttribute("imgCls2", imgCls2);
		model.addAttribute("menu",queryTypeId);
		model.addAttribute("parent", list);
	    return "vrs/imgCls2/edit";
	}
	
	/**
	 * 保存
	 */

	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:imgCls2:add")
	public R save( ImgCls2DO imgCls2){
		
		//换色系统
		if(imgCls2Service.save(imgCls2)>0){
			return R.ok();
		}
		
		return R.error();
	}
	/**
	 * 修改 
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:imgCls2:edit")
	public R update( ImgCls2DO imgCls2){
		imgCls2Service.update(imgCls2);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SuppressWarnings(value = {"unchecked"})
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:imgCls2:remove")
	public R remove(Integer id){
		
		//该分类是否是一级分类，且一级分类下是否存在子分类，如果存在则不允许删除
		ImgCls2DO imgCls2DO = imgCls2Service.get(id);
		boolean flag = false;
		Integer pid = imgCls2DO.getPid();
		if(pid != null && pid == 0) {
			//是否包含子分类
			Integer id2 = imgCls2DO.getId();
			List<ImgClsDO> imgClsPars = imgCls2Service.imgClsPars(id2.toString());
			if(imgClsPars.size() > 0) {
				flag = true;
			}
		}
		if(flag) {
			return R.error(200, MessageConstantVrs.WARNINGMSGS);
		}
		
		//该分类下是否保存Img(Pro)
		Map<String, Object> rootMap = MessageConstantVrs.getRootMap();
		rootMap.put("id", id);
		//级联查询
		List<Map<String, String>> contains = imgCls2Service.contains(rootMap);
		if(contains.size() > 0 || flag) {
			String name = contains.get(0).get("categoryname");
			return R.error(200, name+MessageConstantVrs.WARNINGMSG);
		}
	
		if(imgCls2Service.remove(id)>0){
		return R.ok();
		
		}
		return R.error();
	}
	
	
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:imgCls2:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		
		List<Map<String, String>> contains = null;
		Map<String, Object> rootMap = MessageConstantVrs.getRootMap();
		boolean flag = true;
		StringBuilder sb = new StringBuilder();
		for (Integer id : ids) {
			//key override
			rootMap.put("id", id);
			//级联查询
			contains = imgCls2Service.contains(rootMap);
			if(contains.size() > 0) {
				flag = false;
				sb.append(contains.get(0).get("categoryname")+"、");
			}
		}
		//截取
		String name = sb.length() == 0 ? "" : sb.substring(0, sb.length()-1);
		if(!flag) {
			return R.error(200, name+MessageConstantVrs.WARNINGMSG);
		}
		//删除
		imgCls2Service.batchRemove(ids);
		return R.ok();
	}



	//根据表明查询
	@ResponseBody
	@RequestMapping("/queryTableCls")
	public  List<Map<String,Object>> queryTableCls(String tableName){


		return imgCls2Service.queryTableCls(tableName);

	}
	
	//查所有pid
	@RequestMapping("/imgClsPars")
	@ResponseBody
	public List<ImgClsDO> imgClsPars(String pid){

		return  imgCls2Service.imgClsPars(pid);

	}

	//查询换色二级分类
	@ResponseBody
	@RequestMapping("/queryParentCls")
	public  List<ImgCls2DO> queryParentCls(@RequestParam(defaultValue ="0") String pid){

		return imgCls2DaO.queryParentCls(pid);
	}


	@GetMapping("/querynotParentCls")
	@ResponseBody
	public  List<ImgCls2DO> querynotParentCls(){

		return imgCls2DaO.querynotParentCls();
	}
	
}
