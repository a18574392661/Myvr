package com.bootdo.vrs.pageh5.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface Category {

	/**
	 * @param param
	 * @param req
	 * @param resp
	 * @return 查询分类
	 * @author: wym
	 * @date: 2020年4月17日 下午4:32:22
	 */
	@RequestMapping("/pageList")

	public abstract String pageList(@Param("param") Map<String, String> param, @Param("req") HttpServletRequest req, @Param("resp") HttpServletResponse resp, @Param("model") Model model);

	/**
	 * 
	 * @param param
	 * @param req
	 * @param resp
	 * @param model
	 * @return  画册
	 * @author: wym
	 * @date: 2020年4月21日 上午11:01:25
	 */
	@RequestMapping("/pageListH")

	public abstract String pageListH(@Param("param") Map<String, String> param, @Param("req") HttpServletRequest req, @Param("resp") HttpServletResponse resp, @Param("model") Model model);
	/**
	 * 
	 * @param param
	 * @param req
	 * @param resp
	 * @param model
	 * @return  换色
	 * @author: wym
	 * @date: 2020年4月21日 上午11:01:25
	 */
	@RequestMapping("/pageListG")

	public abstract String pageListG(@Param("param") Map<String, String> param, @Param("req") HttpServletRequest req, @Param("resp") HttpServletResponse resp, @Param("model") Model model);
	/**
	 * 
	 * @param param
	 * @param req
	 * @param resp
	 * @param model
	 * @return  门窗
	 * @author: wym
	 * @date: 2020年4月21日 上午11:01:25
	 */
	@RequestMapping("/pageListM")

	public abstract String pageListM(@Param("param") Map<String, String> param, @Param("req") HttpServletRequest req, @Param("resp") HttpServletResponse resp, @Param("model") Model model);
	

}
