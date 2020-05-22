package com.bootdo.manager.house;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootdo.vrs.common.BaseController;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.domain.ProDO;
import com.bootdo.vrs.service.ProService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/manager/house")
public class ColorChanging extends BaseController{

	@Autowired
	private ProService pro;
	
	@RequestMapping("/toIndex/{path}")
	String toIndex(@PathVariable("path")String path){
		
		System.out.println( "访问了............."+path );
		
		return "vrs/manager/house/"+path;
	}

	@RequestMapping("/getAllProduct")
	public String getAllByMenuId(HttpServletRequest req,HttpServletRequest resp,Model model,@RequestParam(required = false,defaultValue = "1")Integer page) {
		
		//参数
		Map<String, String> rootMap = new HashMap<String, String>(); //MessageConstantVrs.getRootMap();
		rootMap.put("typeid", MessageConstantVrs.HUANSEXXITON+"");

		//分页
		PageHelper.startPage(page,MessageConstantVrs.PAGECOUNT);
		List<ProDO> listPro = pro.getAll(rootMap);
	    if(listPro.size() % MessageConstantVrs.PAGECOUNT != 0) {
	    	Integer roundUp = super.roundUp(listPro.size(), MessageConstantVrs.PAGECOUNT);
	    	for(int i = 0;i < roundUp;i++) {
	    		listPro.add(new ProDO());
	    	}
	    }
	    PageInfo<ProDO> pageInfo = new PageInfo<ProDO>(listPro);
	    
	    // param1 当前页 param2 总页数 param3 显示的页数
	    Map<String, Integer> calculatePageNums = super.calculatePageNums(pageInfo.getPageNum(), pageInfo.getPages(), MessageConstantVrs.PAGENUMS);

	    //拆分集合
		List<List<ProDO>> listSplit = super.listSplit(listPro, MessageConstantVrs.LISTCOUNT);
		
		//保存属性
		model.addAttribute("lsitPro", listSplit);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("start", calculatePageNums.get("start"));
		model.addAttribute("end", calculatePageNums.get("end"));
		
		
		return "vrs/manager/house/color_changing";
	}
	

}
