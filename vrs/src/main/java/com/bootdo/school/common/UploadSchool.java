package com.bootdo.school.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.R;

@Controller
@RequestMapping("/school")
public class UploadSchool {
	
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@ResponseBody
	@RequestMapping("/upload")
	//@RequiresPermissions("vr:leftStyle:add")
	public R uploadPic(@RequestParam("file") MultipartFile file,String name ,HttpServletRequest request) {
		//name区分名字
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/"+name+"/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + name+"/", fileName);
			FileUtil.deleteFile(sysFile.getUrl());
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}


	@ResponseBody
	@RequestMapping("/upload2")
	//@RequiresPermissions("vr:leftStyle:add")
	public R uploadPic2(@RequestParam("file") MultipartFile file,String name ,HttpServletRequest request) {
		//name区分名字
		String imgUrl="";
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/"+name+"/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + name+"/", fileName);
			imgUrl=saveMinImg(bootdoConfig.getUploadPath() + name+"/",sysFile.getUrl());

		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",imgUrl);
		}
		return R.error();
	}



	public  String  saveMinImg(String path,String url){
		String imgUrl="";
		int end = url.lastIndexOf("/");
		String result=url.substring(0,end);
		String newName="s"+url.substring(end+1,url.length());
		// 缩小
		try {
			Thumbnails.of(path+url.substring(end+1,url.length())).size(3000, 3000).toFile(path+"/"+newName);

			imgUrl=result+"/"+newName;
		}
		catch (Exception e){
			e.printStackTrace();
		}


		//Thumbnails.of(result + "qjt.jpg").scale(0.5f).toFile(result + "image_0.5f.jpg");
		//	r.put("simg",simg)

		return imgUrl;

	}

	@RequestMapping("/uploadDescImages")
	@ResponseBody
	public R uploadDescImages(HttpServletRequest request) {
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
	                FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "/articleContent/", fileName);
	                String url = "/files/articleContent/" + fileName;
	                urllist.add(url);
	            } catch (Exception e) {
	                return R.error("图片上传失败 " + i + " => " + e.getMessage());
	            }
	        } else {
	            return R.error("图片上传失败 " + i + " because the file was empty.");
	        }
	    }
	    return R.ok("list", urllist);
	}





}
