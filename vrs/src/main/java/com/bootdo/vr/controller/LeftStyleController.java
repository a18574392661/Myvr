package com.bootdo.vr.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vr.domain.LeftStyleDO;
import com.bootdo.vr.domain.LeftStyleImgsDO;
import com.bootdo.vr.domain.TopStyleDO;
import com.bootdo.vr.service.LeftStyleImgsService;
import com.bootdo.vr.service.LeftStyleService;
import com.bootdo.vr.service.TopStyleService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:19
 */
 
@Controller
@RequestMapping("/vr/leftStyle")
public class LeftStyleController {
	@Autowired
	private LeftStyleService leftStyleService;
	
	@Autowired
	private TopStyleService topStyleService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@Autowired
	private LeftStyleImgsService leftStyleImgsService;
	
	@GetMapping()
	@RequiresPermissions("vr:leftStyle:leftStyle")
	String LeftStyle(Model model){
		model.addAttribute("topStyle", topStyleService.list(new HashMap<String, Object>()));
	    return "vr/leftStyle/leftStyle";
	}
	
	@GetMapping("/topleft")
	@RequiresPermissions("vr:leftStyle:leftStyle")
	String topleft(){
	    return "vr/topleft";
	}
	
	@ResponseBody
	@GetMapping("/queryAllTopAndLeftStyles")
	@RequiresPermissions("vr:leftStyle:leftStyle")
	public R queryAllTopAndLeftStyles(){


		return R.ok("data", leftStyleService.queryAllTopAndLeftStyles());
	}
	
	@ResponseBody
	@GetMapping("/queryLeftStyleByTopStyleId")
	@RequiresPermissions("vr:leftStyle:leftStyle")
	public List<LeftStyleDO> queryLeftStyleByTopStyleId(Integer topStyleId){
		List<LeftStyleDO> leftStyleList = leftStyleService.queryByTopStyleId(topStyleId);
		return leftStyleList;
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:leftStyle:leftStyle")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LeftStyleDO> leftStyleList = leftStyleService.list(query);
		int total = leftStyleService.count(query);
		PageUtils pageUtils = new PageUtils(leftStyleList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/queryLeftStyles")
	@RequiresPermissions("vr:topStyle:topStyle")
	public List<LeftStyleDO> queryLeftStyles(Integer topStyleId){
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("topStyleId", topStyleId);
		List<LeftStyleDO> leftStyles = leftStyleService.list(params);
		return leftStyles;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:leftStyle:add")
	String add(Model model){
		model.addAttribute("topStyles", topStyleService.list(new HashMap<String, Object>()));
		model.addAttribute("topStyleId", 0);
	    return "vr/leftStyle/add";
	}
	
	@GetMapping("/add2/{topStyleId}")
	@RequiresPermissions("vr:leftStyle:add")
	String add(@PathVariable("topStyleId") Integer topStyleId, Model model){
		//查询大风格
		model.addAttribute("topStyles", topStyleService.list(new HashMap<String, Object>()));
		//大风格的id 选中
		model.addAttribute("topStyleId", topStyleId);
	    return "vr/leftStyle/add";
	}

	@GetMapping("/edit/{leftId}")
	@RequiresPermissions("vr:leftStyle:edit")
	String edit(@PathVariable("leftId") Integer leftId,Model model){
		LeftStyleDO leftStyle = leftStyleService.get(leftId);
		model.addAttribute("leftStyle", leftStyle);
		model.addAttribute("topStyles", topStyleService.list(new HashMap<String, Object>()));
		//得到关联的热点图
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leftId", leftId);
		List<LeftStyleImgsDO> list = leftStyleImgsService.list(map);
		model.addAttribute("leftStyleImgs", list);
		
		String imgs = "";
		for( LeftStyleImgsDO img : list ) {
			imgs += img.getImg() + ",";
		}
		
		if( imgs.endsWith(",") ) {
			imgs = imgs.substring(0, imgs.length() -1 );
		}
		model.addAttribute("leftStyleImgsStr", imgs);
	    return "vr/leftStyle/edit";
	}
	
	/**
	 * 保存
	 */
	@Transactional
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:leftStyle:add")
	public R save( LeftStyleDO leftStyle, String imgs){
		if(leftStyleService.save(leftStyle)>0){
			//批量保存图片信息
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("leftId", leftStyle.getLeftId());
			map.put("imgs", imgs.split(","));
			leftStyleImgsService.saveBatch(map);
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:leftStyle:edit")
	public R update( LeftStyleDO leftStyle, String imgs){
		leftStyleService.update(leftStyle);
		
		//移除原来的所有的图片
		leftStyleImgsService.removeByLeftId(leftStyle.getLeftId());
		
		//批量保存图片信息
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("leftId", leftStyle.getLeftId());
		map.put("imgs", imgs.split(","));
		leftStyleImgsService.saveBatch(map);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:leftStyle:remove")
	public R remove( Integer leftId){
		if(leftStyleService.remove(leftId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:leftStyle:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] leftIds){
		leftStyleService.batchRemove(leftIds);
		return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/uploadLeftLogo")
	@RequiresPermissions("vr:leftStyle:add")
	public R uploadPic(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/leftlogo/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "leftlogo/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
	@ResponseBody
	@PostMapping("/uploadCad")
	@RequiresPermissions("vr:leftStyle:add")
	public R uploadCad(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/CAD/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "CAD/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
	@ResponseBody
	@PostMapping("/uploadRdt")
	@RequiresPermissions("vr:leftStyle:add")
	public R uploadRdt(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/leftStyleRdtImgs/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "leftStyleRdtImgs/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
}
