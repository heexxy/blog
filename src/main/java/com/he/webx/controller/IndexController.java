package com.he.webx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.he.webx.domain.Article;
import com.he.webx.mapper.page.Page;
import com.he.webx.service.ArticleService;
import com.he.webx.utils.ParamUtil;

@Controller
public class IndexController {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * @description 首页
	 * @param request
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse resp,Model model){
		Page<Article> page = ParamUtil.getPage(request,Article.class);
		articleService.getPage(page);
		model.addAttribute("page", page);
		return "/index/index.vm";
	}
	
	/**
	 * @descriotion  403错误
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/error-page-403", method = RequestMethod.GET)
	public String errorPage403(HttpServletRequest request) {
		return "/error/404.vm";
	}
	
	/**
	 * @description 404错误
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/error-page-404", method = RequestMethod.GET)
	public String errorPage404(HttpServletRequest request) {
		return "/error/404.vm";
	}

	/**
	 * @description 405错误
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/error-page-500", method = RequestMethod.GET)
	public String errorPage500(HttpServletRequest request) {
		return "/error/404.vm";
	}
	
	/**
	 * @decription 异常
	 * @return
	 */
	@RequestMapping(value="/exception-page" , method = RequestMethod.GET)
	public String exceptionPage(){
		return "/error/exception.vm";
	}
	
}
