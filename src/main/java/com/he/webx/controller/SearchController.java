package com.he.webx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.he.webx.domain.Article;
import com.he.webx.mapper.page.Page;
import com.he.webx.service.ArticleService;
import com.he.webx.utils.CommonUtils;
import com.he.webx.utils.ParamUtil;

@Controller
@RequestMapping(value = "/search")
public class SearchController {
	
	@Autowired
	private ArticleService articleService;

	/**
	 * @description 标签查询方法
	 * @param request
	 * @param response
	 * @param model
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/tags" , method = RequestMethod.GET)
	public String tagSearch(HttpServletRequest request,
			HttpServletResponse response, Model model, @RequestParam String tag) {
		tag = CommonUtils.encodeStr(tag);
		Page<Article> page = ParamUtil.getPage(request, Article.class);
		articleService.getArticleByTag(page, tag);
		int count = page.getTotal();
		model.addAttribute("tag", tag);
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "/search/result.vm";
	}
	
	/**
	 * @description 根据关键字搜索标题
	 * @param request
	 * @param response
	 * @param model
	 * @param word
	 * @return
	 */
	@RequestMapping(value = "/word" , method = RequestMethod.GET)
	public String wordSearch(HttpServletRequest request,
			HttpServletResponse response, Model model, @RequestParam String word){
		word = CommonUtils.encodeStr(word);
		Page<Article> page = ParamUtil.getPage(request, Article.class);
		articleService.getArticleByWord(page, word);
		int count = page.getTotal();
		model.addAttribute("tag", word);
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "/search/result.vm";
	}
}
