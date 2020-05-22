package com.bootdo.vr.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vr.domain.AppVersionDO;
import com.bootdo.vr.service.AppVersionService;

/**
 * 
 * 
 * @author chglee
 * @email test@163.com
 * @date 2019-01-07 15:45:31
 */
 
@Controller
@RequestMapping("/vr/appVersion")
public class AppVersionController {
	@Autowired
	private AppVersionService appVersionService;
	
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("lc:appVersion:appVersion")
	String AppVersion(){
	    return "lc/appVersion/appVersion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("lc:appVersion:appVersion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AppVersionDO> appVersionList = appVersionService.list(query);
		int total = appVersionService.count(query);
		PageUtils pageUtils = new PageUtils(appVersionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("lc:appVersion:add")
	String add(){
	    return "lc/appVersion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("lc:appVersion:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AppVersionDO appVersion = appVersionService.get(id);
		model.addAttribute("appVersion", appVersion);
	    return "lc/appVersion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("lc:appVersion:add")
	public R save( AppVersionDO appVersion){
		if(appVersionService.save(appVersion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("lc:appVersion:edit")
	public R update( AppVersionDO appVersion){
		appVersionService.update(appVersion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("lc:appVersion:remove")
	public R remove( Integer id){
		if(appVersionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("lc:appVersion:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		appVersionService.batchRemove(ids);
		return R.ok();
	}
	
	@GetMapping("/updateApp")
	@RequiresPermissions("lc:appVersion:add")
	String updateApp(){
	    return "lc/appVersion/updateApp";
	}
	
	@ResponseBody
	@PostMapping("/uploadapk")
	@RequiresPermissions("lc:appVersion:edit")
	public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String client = request.getParameter("client");
		String version = request.getParameter("version");
		
		if( StringUtils.isEmpty(version) || StringUtils.isEmpty(client) ) {
			return R.error("所有项都必须填写哦！");
		}
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToVersion(fileName, version);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/apk/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "apk/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			//把信息保存到version表
			AppVersionDO versions = new AppVersionDO();
			versions.setClient(client);
			versions.setVersion(version);
			versions.setUrl(sysFile.getUrl());
			appVersionService.updateVersion(versions);//上传新版本的apk
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
}
