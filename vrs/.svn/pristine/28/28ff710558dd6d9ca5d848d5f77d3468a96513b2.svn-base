package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.dao.TestDao;
import com.bootdo.system.domain.TestDO;
import com.bootdo.system.service.TestService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 测试案例
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-07 16:24:49
 */
 
@Controller
@RequestMapping("/system/test")
public class TestController {
	@Autowired
	private TestService testService;
	
	@Autowired
	private TestDao testDao;
	
	@GetMapping()
	@RequiresPermissions("system:test:test")
	String Test(){
	    return "system/test/test";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:test:test")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestDO> testList = testService.list(query);
		int total = testService.count(query);
		PageUtils pageUtils = new PageUtils(testList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:test:add")
	String add(ModelMap map){
		//查询下拉框
		map.put("rs", testDao.queryRoles());
	    return "system/test/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:test:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TestDO test = testService.get(id);
		model.addAttribute("test", test);
		
		model.addAttribute("rs",testDao.queryRoles());
	    return "system/test/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:test:add")
	public R save( TestDO test){
		if(testService.save(test)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:test:edit")
	public R update( TestDO test){
		testService.update(test);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:test:remove")
	public R remove( Long id){
		if(testService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:test:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		testService.batchRemove(ids);
		return R.ok();
	}
	
}
