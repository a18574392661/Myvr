package com.bootdo.vrs.pageh5.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.school.annotations.LoginRequired;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.vrs.common.BaseController;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.ImgCls2Dao;
import com.bootdo.vrs.dao.TitleClsDao;
import com.bootdo.vrs.domain.*;
import com.bootdo.vrs.service.ImgCls2Service;
import com.bootdo.vrs.service.ImgCls3Service;
import com.bootdo.vrs.service.ImgCls4Service;
import com.bootdo.vrs.service.ProService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vrs/houses")
public class CategorysController extends BaseController implements Category{


	@Autowired
	private ProService pro;

	@Autowired
	private RedisUtil redisUtil;


	@Autowired
	private ImgCls3Service imgCls3Service;

	@Autowired
	private TitleClsDao titleClsDao;
	
	@Autowired
	private ImgCls2Service imgCls2Service;


	@Autowired
	private ImgCls2Dao imgCls2Dao;

	@Autowired
	private ImgCls4Service imgCls4Service;


	@Autowired
	private BootdoConfig bootdoConfig;
	
	static Map<String,Integer> typemap = new HashMap<String, Integer>();



	@RequestMapping("/to_down")
	public  String test(){

		return  MessageConstantVrs.VRSH5_PAGE+"h5/downapp";
	}




	@RequestMapping("/down2")
	public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
		File f = new File("http://ysjvr.cn/files/qjt/e865c25e92e54a29bc7d8812fb23dd4b.jpg");
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;

		response.reset(); // 非常重要
		if (false) { // 在线打开方式
			URL u = new URL("file:///" + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
			// 文件名应该编码成UTF-8
		} else { // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}

	@RequestMapping("/downs")
//	@ResponseBody
	public void downloadNet(HttpServletResponse response) throws MalformedURLException {
		response.setHeader("content-Type", "mimeType");
		response.setHeader("content-disposition", "attachment;filename=aaa.jpg");

		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;
		URL url = new URL("http://ysjvr.cn/files/qjt/e865c25e92e54a29bc7d8812fb23dd4b.jpg");
		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream("d:/abc.gif");
			byte[] buffer = new byte[1204];
			int length;
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {

		}
	}


	@GetMapping("/index")
	@LoginRequired(loginSuccess = false)
	String toIndex(ModelMap map){

		map.put("ste",1);
		//首页内容
		/*Map<String,Object> mapCount=pro.queryIndex();*/
		map.put("maps",saveIndex());



		return  MessageConstantVrs.VRSH5_PAGE+"new/index"; // 以前的 MessageConstantVrs.VRSH5_PAGE+"index";
	}
	
	//记载类型
	static {
		typemap.put("custom_design", MessageConstantVrs.PARCLASSDINZHI); //定制设计
		typemap.put("color_changing", MessageConstantVrs.HUANSEXXITON); //换色系统
		typemap.put("doors_and_windows", MessageConstantVrs.MENGCHUANGSHEJI);//门窗设计
		typemap.put("website_design", MessageConstantVrs.HUACEXITON);//高端画册
	}



	
	@GetMapping("/toPage/{path}")
	@LoginRequired(loginSuccess = false)
	String toPage( @PathVariable("path")String path){
		return MessageConstantVrs.VRSH5_PAGE+path;
	}

	@RequestMapping("/getAllProduct/{realPath}")
	@LoginRequired(loginSuccess = false)
	public String getAllByMenuId(HttpServletRequest req,HttpServletRequest resp,
			Model model,@RequestParam(required = false,defaultValue = "1")Integer page,
			@PathVariable("realPath")String realPath,@RequestParam(value="categoryid",required=false)String categoryid) {




			return "vrs/manager/house/"+realPath;
	}

	@Override
	@LoginRequired(loginSuccess = false)
	public String pageList(@RequestParam Map<String, String> param, HttpServletRequest req, HttpServletResponse resp, Model model) {
		model.addAttribute("maps",saveIndex());
		//转发路径

		String url = param.get("requetPaht");
	
		if(url == null) {
			url = "color_changing";
		}else if("website_design".equals(url)){
			//高端画册查询
			List<ImgCls3DO> list = imgCls3Service.list(null);
			//截取
			list = list.size() > 3 ? list.subList(0, 3) : list;
			model.addAttribute("list",list);
			
		}
		//获取请求路径
		String requestUrl = req.getRequestURL().toString();
		model.addAttribute("requestUrl",requestUrl);
		//存放地页码中
		model.addAttribute("requetPaht", url);
		//分页参数
		Integer page = 1;
		if(!StringUtils.isEmpty(param.get("page"))) {
			page = Integer.parseInt(param.get("page"));
		}
		//分页
		PageHelper.startPage(page,MessageConstantVrs.PAGECOUNT);
		//查询条件
		Integer typeid = typemap.get(url);
	  	TitleClsDO titleClsDO=titleClsDao.queryTypeId(typeid);
		param.put("typeid", String.valueOf(titleClsDO.getId()));
		//一级分类查询，换色系统/高端画册
		if(!StringUtils.isEmpty(param.get("categoryid")) && 
		   !"null".equals(param.get("categoryid")) && 
		   ("color_changing".equals(url) || "website_design".equals(url))) {
			//条件
			param.put("cid", String.valueOf(param.get("categoryid")));
			model.addAttribute("cid", String.valueOf(param.get("categoryid")));
		}

		List<ProDO> listPro = pro.getAll(param);
		PageHelper.startPage(page,9);
		super.PageHelper(model,listPro);
		
		
		// TODO Auto-generated method stub
		return MessageConstantVrs.VRSH5_PAGE+url;
	}
	
	//高端
	@Override
	@LoginRequired(loginSuccess = false)
	public String pageListH(@RequestParam Map<String, String> param, HttpServletRequest req, HttpServletResponse resp, Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("ste",5);
		model.addAttribute("maps",saveIndex());
		//根据typeid获取id
		TitleClsDO titleClsDO = titleClsDao.queryTypeId(MessageConstantVrs.HUACEXITON);
		//分页参数
		Integer page = 1;
		if(!StringUtils.isEmpty(param.get("page"))) {
			page = Integer.parseInt(param.get("page"));
		}
		//页数与条数
		PageHelper.startPage(page,MessageConstantVrs.PAGECOUNT);
		param.put("typeid", String.valueOf(titleClsDO.getId()));
		//分类查询条件
		String cid = param.get("cid");
		param.remove("cid");
		if(!StringUtils.isEmpty(cid) && !"null".equals(cid)) {
			param.put("cid",cid);
			model.addAttribute("cid", cid);
		}
		
		//分页查询
		List<ProDO> all = pro.getAll(param);
		
		PageInfo<ProDO> pageInfo= new PageInfo<ProDO>(all);
		model.addAttribute("pageInfo", pageInfo);
		//将集合分为三个集合
		List<List<ProDO>> listPro = listSplit(all, MessageConstantVrs.LISTCOUNT);
		model.addAttribute("listPro", listPro);
		
		//展示分类
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", "1");
		List<ImgCls3DO> list = imgCls3Service.list(params);

		//
		list = list.size() > 3 ? list.subList(0, 3) : list;
		model.addAttribute("category", list);
		
		//获取请求urlpageListG
		StringBuffer requestUrl = req.getRequestURL();
		model.addAttribute("requestUrl", requestUrl);
		
		//param1 当前页 param2 总页数 param3 显示的页数
	    Map<String, Integer> calculatePageNums = calculatePageNums(pageInfo.getPageNum(), pageInfo.getPages(), MessageConstantVrs.PAGENUMS);
	    model.addAttribute("start", calculatePageNums.get("start"));
		model.addAttribute("end", calculatePageNums.get("end"));
	    
	    
		//页面跳转
		return MessageConstantVrs.VRSH5_PAGE+"website_design";
	}

	//换色
	@Override
	@LoginRequired(loginSuccess = false)
	public String pageListG(@RequestParam Map<String, String> param, HttpServletRequest req, HttpServletResponse resp, Model model) {
		model.addAttribute("ste",3);
		// TODO Auto-generated method stub
		model.addAttribute("maps",saveIndex());
		//根据typeid获取id

		TitleClsDO titleClsDO = titleClsDao.queryTypeId(MessageConstantVrs.HUANSEXXITON);
		//分页参数
		Integer page = 1;
		if(!StringUtils.isEmpty(param.get("page"))) {
			page = Integer.parseInt(param.get("page"));
		}
		//页数与条数
		PageHelper.startPage(page,MessageConstantVrs.PAGECOUNT);
		param.put("typeid", String.valueOf(titleClsDO.getId()));
		//分类查询条件
		String cid = param.get("cid");
		param.remove("cid");
		if(!StringUtils.isEmpty(cid) && !"null".equals(cid)) {
			param.put("cid", cid);
			model.addAttribute("cid", cid);
		}
		
		//查询
		List<ProDO> all = pro.getAll(param);
		PageInfo<ProDO> pageInfo= new PageInfo<ProDO>(all);
		model.addAttribute("pageInfo", pageInfo);
		//将集合分为三个集合
		List<List<ProDO>> listPro = listSplit(all, MessageConstantVrs.LISTCOUNT);
		model.addAttribute("listPro", listPro);
		
		//展示分类
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("offset", 0);
		params.put("limit", 3);
		params.put("status", "1");
		params.put("pid","0");
		List<ImgCls2DO> list = imgCls2Service.list(params);
		model.addAttribute("category", list);
		
		
		//获取请求url
		StringBuffer requestUrl = req.getRequestURL();
		model.addAttribute("requestUrl", requestUrl);
		
		//param1 当前页 param2 总页数 param3 显示的页数
	    Map<String, Integer> calculatePageNums = calculatePageNums(pageInfo.getPageNum(), pageInfo.getPages(), MessageConstantVrs.PAGENUMS);
	    model.addAttribute("start", calculatePageNums.get("start"));
		model.addAttribute("end", calculatePageNums.get("end"));
		
		
		//页面跳转
		return MessageConstantVrs.VRSH5_PAGE+"color_changing";
	}


	//门窗
	@Override
	@LoginRequired(loginSuccess = false)
	public String pageListM(@RequestParam Map<String, String> param, HttpServletRequest req, HttpServletResponse resp, Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("ste",4);
		model.addAttribute("maps",saveIndex());
		//根据typeid获取id
		TitleClsDO titleClsDO = titleClsDao.queryTypeId(MessageConstantVrs.MENGCHUANGSHEJI);
		//分页参数
		Integer page = 1;
		if(!StringUtils.isEmpty(param.get("page"))) {
			page = Integer.parseInt(param.get("page"));
		}
		//页数与条数
		PageHelper.startPage(page,MessageConstantVrs.PAGECOUNT);
		param.put("typeid", String.valueOf(titleClsDO.getId()));
		//分类查询条件
		String cid = param.get("cid");
		param.remove("cid");

		//分页查询
		List<ProDO> all = pro.getAll(param);
		PageInfo<ProDO> pageInfo= new PageInfo<ProDO>(all);
		model.addAttribute("pageInfo", pageInfo);
		//将集合分为三个集合
		List<List<ProDO>> listPro = listSplit(all, MessageConstantVrs.LISTCOUNT);
		model.addAttribute("listPro", listPro);
		
		//获取请求url
		StringBuffer requestUrl = req.getRequestURL();
		model.addAttribute("requestUrl", requestUrl);
		
		//param1 当前页 param2 总页数 param3 显示的页数
	    Map<String, Integer> calculatePageNums = calculatePageNums(pageInfo.getPageNum(), pageInfo.getPages(), MessageConstantVrs.PAGENUMS);
	    model.addAttribute("start", calculatePageNums.get("start"));
		model.addAttribute("end", calculatePageNums.get("end"));
		
		
		//页面跳转
		return MessageConstantVrs.VRSH5_PAGE+"doors_and_windows";
	}
	
	
	//目前没用大
	@RequestMapping(value = "/showImg")
    @ResponseBody
	@LoginRequired(loginSuccess = false)
	public void showImg( HttpServletRequest request,HttpServletResponse response,String pathName) {
	
		if(pathName == null) {
			return;
		}


		//绝对路径
		pathName = bootdoConfig.getUploadPath() + pathName;
		File imgFile = new File(pathName);
		//文件是否存在，判断图片
		if(imgFile.exists() && isImage(pathName)) {
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


	@ResponseBody
	@RequestMapping("/getClsChild")
	public  List<ImgCls2DO> getClsChild(String pid){
		return  imgCls2Dao.queryParentCls(pid);
	}



	@ResponseBody
	@RequestMapping("/getClsAll")
	public  List<ImgCls2DO> getClsAll(String pid){
		Map<String,Object> map=new HashMap<>();
		map.put("pid",pid);
		map.put("status","1");
		return  imgCls2Dao.list(map);
	}

}
