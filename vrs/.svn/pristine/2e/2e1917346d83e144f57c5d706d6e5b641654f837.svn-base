package com.bootdo.vr.controller;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.util.StringUtils;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vr.domain.LeftStyleDO;
import com.bootdo.vr.domain.RomeTypeDO;
import com.bootdo.vr.service.LeftStyleService;
import com.bootdo.vr.service.RomeTypeService;
import com.bootdo.vr.service.RoomTypeImgsService;
import com.bootdo.vr.service.TopStyleService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:20
 */
 
@Controller
@RequestMapping("/vr/romeType")
public class RomeTypeController {
	@Autowired
	private RomeTypeService romeTypeService;
	@Autowired
	private TopStyleService topStyleService;
	@Autowired
	private LeftStyleService leftStyleService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@Autowired
	private RoomTypeImgsService roomTypeImgsService;
	
	@GetMapping()
	@RequiresPermissions("vr:romeType:romeType")
	String RomeType(Model model){
		model.addAttribute("topStyle", topStyleService.list(new HashMap<String, Object>()));
	    return "vr/romeType/romeType";
	}
	
	@ResponseBody
	@GetMapping("/queryRomeTypeByLeftStyleId")
	@RequiresPermissions("vr:romeType:romeType")
	public List<RomeTypeDO> queryRomeTypeByLeftStyleId(Integer leftStyleId){
		List<RomeTypeDO> romeTypeList = romeTypeService.queryByLeftStyleId(leftStyleId);
		return romeTypeList;
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:romeType:romeType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RomeTypeDO> romeTypeList = romeTypeService.list(query);
		int total = romeTypeService.count(query);
		PageUtils pageUtils = new PageUtils(romeTypeList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/queryRomeStyles")
	@RequiresPermissions("vr:topStyle:topStyle")
	public List<RomeTypeDO> queryRomeStyles(Integer leftStyleId){
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("leftStyleId", leftStyleId);
		List<RomeTypeDO> romeTypeList = romeTypeService.list(params);
		return romeTypeList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:romeType:add")
	String add(Model model){
		model.addAttribute("topStyles", topStyleService.list(new HashMap<String, Object>()));
		model.addAttribute("leftStyles", new ArrayList<LeftStyleDO>());
		model.addAttribute("topStyleId", 0);
		model.addAttribute("leftStyleId", 0);
	    return "vr/romeType/add";
	}
	
	@GetMapping("/add2/{topStyleId}/{leftStyleId}")
	@RequiresPermissions("vr:romeType:add")
	String add(@PathVariable("topStyleId") Integer topStyleId, @PathVariable("leftStyleId") Integer leftStyleId, Model model){
		model.addAttribute("topStyles", topStyleService.list(new HashMap<String, Object>()));
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("topStyleId", topStyleId);
		model.addAttribute("leftStyles", leftStyleService.list(param));
		model.addAttribute("topStyleId", topStyleId);
		model.addAttribute("leftStyleId", leftStyleId);
	    return "vr/romeType/add";
	}

	@GetMapping("/edit/{romeId}")
	@RequiresPermissions("vr:romeType:edit")
	String edit(@PathVariable("romeId") Integer romeId,Model model){
		RomeTypeDO romeType = romeTypeService.get(romeId);
		model.addAttribute("romeType", romeType);
		
		String imgs = romeType.getImgs();
		List<String> imgsList = new ArrayList<String>();
		if( StringUtils.isEmpty(imgs) ) {
			model.addAttribute("rdtImgs", imgsList);
		} else {
			String[] imgsArr = imgs.split(",");
			for( int i = 0; i < imgsArr.length; i++) {
				imgsList.add(imgsArr[i]);
			}
			model.addAttribute("rdtImgs", imgsList);
		}
		
		
		model.addAttribute("topStyles", topStyleService.list(new HashMap<String, Object>()));
		model.addAttribute("leftStyles", leftStyleService.queryByTopStyleId(romeType.getTopStyleId()));
	    return "vr/romeType/edit";
	}
	
	/**
	 * 保存
	 */
	@Transactional
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:romeType:add")
	public R save( RomeTypeDO romeType){
		if(romeTypeService.save(romeType)>0){
			
			//保存所有相关的图片
			String imgStr = romeType.getImgs();
			if( !StringUtils.isEmpty(imgStr) ) {
				String[] imgs = imgStr.split(",");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("imgs", imgs);
				params.put("roomId", romeType.getRomeId());
				roomTypeImgsService.saveBatch(params);
			}
			
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
	@RequiresPermissions("vr:romeType:edit")
	public R update( RomeTypeDO romeType){
		romeTypeService.update(romeType);
		
		//移除所有相关的图片
		roomTypeImgsService.removeByRoomId(romeType.getRomeId());
		
		//保存新的图片
		String imgStr = romeType.getImgs();
		if( !StringUtils.isEmpty(imgStr) ) {
			String[] imgs = imgStr.split(",");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("imgs", imgs);
			params.put("roomId", romeType.getRomeId());
			roomTypeImgsService.saveBatch(params);
		}
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:romeType:remove")
	public R remove( Integer romeId){
		if(romeTypeService.remove(romeId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:romeType:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] romeIds){
		romeTypeService.batchRemove(romeIds);
		return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/uploadImgs")
	@RequiresPermissions("vr:romeType:add")
	public R uploadCad(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/romeTypeImgs/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "romeTypeImgs/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
	/*@RequestMapping("/uploadImages")
	@ResponseBody
	public R handleFileUpload(HttpServletRequest request) {
	   // MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
	    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
	    System.out.println("files" + files);
		//接收前端传过来的字段
	    //String name = params.getParameter("name");
	    MultipartFile file = null;
	    //先设置一个数组来装file路径
	    //List imgs = new ArrayList();
	   //设置图片路径
		//private final String UPLOAD_FOLDER = "pic/";
	    List<String> urllist = new ArrayList<String>();
	    for (int i = 0; i < files.size(); ++i) {
	        file = files.get(i);
	        if (!file.isEmpty()) {
	            try {
	            	// 获取图片的文件名
	                String fileName = file.getOriginalFilename();
	                fileName = FileUtil.renameToUUID(fileName);
	                FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "/goodsDetails/", fileName);
	                String url = "/files/goodsDetails/" + fileName;
	                urllist.add(url);
	                
	                // 获取图片的扩展名
	                String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
	                // 新的图片文件名 = 获取时间戳+"."图片扩展名
	                String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
	                //图片路径
	                Path path = Paths.get(UPLOAD_FOLDER + newFileName);
					//System.out.println("lj: " + path);
	                imgs.add(path);
	                //如果没有files文件夹，则创建
	                if (!Files.isWritable(path)) {
	                    Files.createDirectories(Paths.get(UPLOAD_FOLDER));
	                }
	                //文件写入指定路径
	                Files.write(path, bytes);
	            } catch (Exception e) {
	                return R.error("图片上传失败 " + i + " => " + e.getMessage());
	            }
	        } else {
	            return R.error("图片上传失败 " + i + " because the file was empty.");
	        }
	    }
	    return R.ok("list", urllist);
	}*/
	
	
}
