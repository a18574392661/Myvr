package com.bootdo.article.controller;

import com.bootdo.article.domain.ArticleDO;
import com.bootdo.article.service.ArticleService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ZxingEAN13Code;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-11 19:47:36
 */

@Controller
@RequestMapping("/article/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("article:article:article")
	String Article() {
		return "article/article/article";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("article:article:article")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ArticleDO> articleList = articleService.list(query);
		for (int i = 0; i < articleList.size(); i++) {
			articleList.get(i).setBz(bootdoConfig.getUploadPath());
		}
		int total = articleService.count(query);
		PageUtils pageUtils = new PageUtils(articleList, total);
		return pageUtils;
	}
	
	 /**
	  * 图片回显
	  * @param request
	  * @param response
	  * @param pathName
	  */
	@RequestMapping(value = "/showImg")
    @ResponseBody
	public void showImg( HttpServletRequest request,HttpServletResponse response,String pathName) {
	
		if(pathName == null) {
			return;
		}
		//绝对路径
		pathName = bootdoConfig.getUploadPath() + pathName;
		File imgFile = new File(pathName);
		//文件是否存在
		if(imgFile.exists()) {
			FileInputStream fin = null;
			OutputStream output=null;
			try {
			//IO输出
			output = response.getOutputStream();
			fin = new FileInputStream(imgFile);
			byte[] arr = new byte[1024*10];
			int n;
			while((n=fin.read(arr))!= -1) {
				output.write(arr, 0, n);
			}
			output.flush();
			} catch (Exception e) {
				// TODO: handle exception
				 e.printStackTrace();
			}
			try {
			    output.close();
			   } catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		}
	}
	/**
	 * 根据uuid获取详情
	 * @param params
	 * @return
	 */
	@GetMapping("/selectOne/{qrcodeUrl}")
	public String selectOne(@PathVariable("qrcodeUrl") String qrcodeUrl, HttpServletRequest request) {

		Map<String, Object> params = new HashMap<String, Object>();
		String qrcode = request.getParameter("qrcode");
		params.put("value", qrcodeUrl);
		ArticleDO articleDO = articleService.selectOne(params);
		//条件转发
		if(!StringUtils.isEmpty(qrcode)) {
			request.setAttribute("qrcodeurl", "/article/article/showImg/?pathName="+qrcode);
			return "article/article/qrcode";
		}else {
			request.setAttribute("ht", articleDO);
			return "article/article/xy";
		}
		
	}

	@GetMapping("/add")
	@RequiresPermissions("article:article:add")
	String add() {
		return "article/article/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("article:article:edit")
	String edit(@PathVariable("id") Integer id, Model model) {
		ArticleDO article = articleService.get(id);
		model.addAttribute("article", article);
		return "article/article/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("article:article:add")
	public R save(ArticleDO article, HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString();

		String url = request.getRequestURL() + "";
		String qrcodeUrl = url.replace("/article/article/save", "/article/article/selectOne/" + uuid + ".png");
		//重新生成二维码
		createImg(article,uuid, qrcodeUrl);
		if (articleService.save(article) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	  * 重新生成二维码
	 */
	@ResponseBody
	@PostMapping("/reCreateImg")
	@RequiresPermissions("article:article:readd")
	public R reCreateImg(ArticleDO article, HttpServletRequest request) {
		
		String uuid = UUID.randomUUID().toString();
		String url = request.getRequestURL() + "";
		String qrcodeUrl = url.replace("/article/article/reCreateImg", "/article/article/selectOne/" + uuid + ".png");
		//重新生成二维码
		createImg(article,uuid, qrcodeUrl);
		if (articleService.update(article) > 0) {
			return R.ok();
		}
		return R.error();
	}
	

	/**
	 * 生成二维码
	 * 
	 * @param article
	 * @param request
	 */
	public void createImg(ArticleDO article, String uuid, String host) {
		// 生成二维码
		String img = uuid + ".png";
		String encodeImgPath = bootdoConfig.getUploadPath() + img;
		
		// 二维码图片
		article.setQrcodeUrl(host);
		article.setImg(img);
		int width = 250, height = 250;

		// 二维码上传
		ZxingEAN13Code.encodeQrcode(host, width, height, encodeImgPath);
	}

	/**
	  * 获取ip
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	/**
	  * 下载二维码
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/download/{qrcodeUrl}")
	@RequiresPermissions("article:article:download")
	public void download(@PathVariable("qrcodeUrl") String qrcodeUrl, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String img = bootdoConfig.getUploadPath() + qrcodeUrl;
		File file = new File(img);
		if (!file.exists()) {
		
		}else {
			// 单个下载
			downloadFile(request, response, img);
		}
	}
	

	/**
	 * 批量下载二维码
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/batchDownload")
	@RequiresPermissions("article:article:batchDownload")
	public void batchDownload(@RequestParam("ids")String arrayids, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 批量下载
		if (arrayids == null) {
			return;
		}
		String[] ids = arrayids.split(",");
		List<ArticleDO> list = articleService.batchList(ids);
		if (list == null || list.size() == 0) {
			return;
		}
		// 源文件
		String[] path = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String imgPath = bootdoConfig.getUploadPath() + list.get(i).getImg();
			path[i] = imgPath;
		}
		// 批量下载
		downloadFiles(request, response, path,list);
	}

	/**
	  * 文件下载---单个
	 * 
	 * @throws FileNotFoundException
	 */
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {

		// 时间戳
		String uuid = formmatTime();
		// 响应头设置
		response.setHeader("content-Type", "mimeType");
		response.setHeader("content-disposition", "attachment;filename=" + uuid + ".png");
		// IO读写
		FileInputStream fis = new FileInputStream(path);
		ServletOutputStream out = response.getOutputStream();
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = fis.read(bytes)) != -1) {
			out.write(bytes, 0, len);
		}
		// 释放资源
		out.flush();
		out.close();
		fis.close();
	}

	/**
	 * 文件下载---批量
	 * 
	 * @throws FileNotFoundException
	 */
	public void downloadFiles(HttpServletRequest request, HttpServletResponse response, String[] path,List<ArticleDO> list)
			throws Exception {

		// 设置响应头
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setContentType("application/octet-stream; charset=UTF-8");
		
		// zip
		String billname = "QRcode" + formmatTime();
		String downloadName = billname + ".zip";
		// 返回客户端浏览器的版本号、类型
		String agent = request.getHeader("USER-AGENT");
		try {
			// 针对IE或者以IE为内核的浏览器：
			if (agent.contains("MSIE") || agent.contains("Trident")) {
				downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
			} else {
				// 非IE浏览器的处理：
				downloadName = new String(downloadName.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment;fileName=\"" + downloadName + "\"");

		// 设置压缩流：直接写入response，实现边压缩边下载
		ZipOutputStream zipos = null;
		try {
			zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
			zipos.setMethod(ZipOutputStream.DEFLATED); // 设置压缩方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 循环将文件写入压缩流
		DataOutputStream os = null;
		for (int i = 0; i < path.length; i++) {
			File file = new File(path[i]);
			if (!file.exists()) {
				
			} else {
				try {
					// 添加ZipEntry，并ZipEntry中写入文件流
					String fileName = list.get(i).getId() +"-"+ list.get(i).getTitle()+"-"+list.get(i).getCategoryname()+".png";
					zipos.putNextEntry(new ZipEntry(fileName));
					os = new DataOutputStream(zipos);
					InputStream is = new FileInputStream(file);
					byte[] b = new byte[100];
					int length = 0;
					while ((length = is.read(b)) != -1) {
						os.write(b, 0, length);
					}
					is.close();
					zipos.closeEntry();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 关闭流
		try {
			os.flush();
			os.close();
			zipos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	  * 获取map
	 * @return
	 */
	public Map<String, Object> getRootMap() {

		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	/**
	  * 获取时间戳
	 * @return
	 */
	public String formmatTime() {
		// 生成时间戳
		String uuid = LocalDateTime.now().toString().replace(".", "");
		uuid = uuid.replace("-", "");
		uuid = uuid.replace(":", "");
		return uuid;
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("article:article:edit")
	public R update(ArticleDO article) {
		articleService.update(article);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("article:article:remove")
	public R remove(Integer id) {
		if (articleService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("article:article:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids) {
		articleService.batchRemove(ids);
		return R.ok();
	}

}
