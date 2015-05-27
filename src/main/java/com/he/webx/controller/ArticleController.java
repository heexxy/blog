package com.he.webx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.he.webx.domain.Article;
import com.he.webx.domain.Person;
import com.he.webx.mapper.page.Page;
import com.he.webx.service.ArticleService;
import com.he.webx.utils.CommonUtils;
import com.he.webx.utils.ParamUtil;
import com.he.webx.utils.TimeUtils;
import com.he.webx.utils.ValidUtils;
import com.he.webx.utils.constants.WebxConstants;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	/**
	 * @description 文章列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String articleList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Article> page = ParamUtil.getPage(request, Article.class);
		articleService.getPage(page);
		model.addAttribute("page", page);
		return "/cms/article/list.vm";
	}

	/**
	 * @description 文章详细页面
	 * @param request
	 * @param response
	 * @param model
	 * @param item
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String article(HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable String id) {
		Article article = articleService.getArticleById(id);
		articleService.updateClick(id);
		model.addAttribute("article", article);
		return "/cms/article/article.vm";
	}

	/**
	 * @description 打开文章新增页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newarticle(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Person p = (Person) session.getAttribute(WebxConstants.PERSON_SESSION);
		if (p == null) {
			return WebxConstants.REDIRECT + "/";
		}
		return "/cms/article/article-new.vm";
	}

	/**
	 * @description 打开文章修改页面
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editArticle(HttpServletResponse response,
			HttpServletRequest request, HttpSession session, Model model,
			@PathVariable String id) {
		Person p = (Person) session.getAttribute(WebxConstants.PERSON_SESSION);
		Article article = articleService.getArticleById(id);
		if (p == null || ValidUtils.isNull(article)) {
			return WebxConstants.REDIRECT + "/";
		}
		model.addAttribute("article", article);
		return "/cms/article/article-edit.vm";
	}

	/**
	 * @description 文章保存
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @param title
	 * @param subTitle
	 * @param content
	 * @param summary
	 * @param item
	 * @param tagsinput
	 * @return
	 */
	@RequestMapping(value = "/new/post", method = RequestMethod.POST)
	public String post(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session,
			String title, String subTitle, String content, String summary,
			String item, String tagsinput) {
		Person p = (Person) session.getAttribute(WebxConstants.PERSON_SESSION);
		if (p == null) {
			return WebxConstants.REDIRECT + "/";
		}
		
		String maxId = articleService.getMaxId();
		Article article = new Article();
		article.setItemId(item);
		article.setClickCount(0);
		article.setContent(content);
		article.setSubTitle(subTitle);
		article.setTitle(title);
		article.setTags(tagsinput);
		article.setSummary(summary);
		article.setCreator(p.getName());
		article.setAuthor(p.getName());
		article.setIsPublish(WebxConstants.PUBLISH_YES);
		article.setTop(WebxConstants.TOP_NO);
		article.setId(CommonUtils.getNextID(maxId));
		article.setCreateTime(TimeUtils.getNowTime());
		article.setPublishTime(TimeUtils.getNowTime());
		articleService.insetArticle(article);
		return WebxConstants.REDIRECT + "/article/list";
	}
	
	@RequestMapping(value = "/edit/post", method = RequestMethod.POST)
	public String editPost(HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession session,
			String title, String subTitle, String content, String summary,
			String item, String tagsinput,String id) {
		Person p = (Person) session.getAttribute(WebxConstants.PERSON_SESSION);
		if (p == null) {
			return WebxConstants.REDIRECT + "/";
		}
		Article article = articleService.getArticleById(id);
		article.setItemId(item);
		article.setContent(content);
		article.setSubTitle(subTitle);
		article.setTitle(title);
		article.setTags(tagsinput);
		article.setSummary(summary);
		article.setEditor(p.getName());
		article.setEditTime(TimeUtils.getDateTime());
		articleService.updateArticleById(article);
		return WebxConstants.REDIRECT + "/article/list";
	}

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public String nextPage(HttpServletRequest request,
			HttpServletResponse response, Model model, @RequestParam String id,
			@RequestParam String type, @RequestParam String item) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("type", type);
		Article article = articleService.getNextArticle(map);
		if (ValidUtils.isNull(article)) {
			return WebxConstants.REDIRECT + "/article/" + id;
		}
		return WebxConstants.REDIRECT + "/article/" + article.getId();
	}
	
	@RequestMapping(value="/edit-list",method = RequestMethod.GET)
	public String editList(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
		if(session.getAttribute(WebxConstants.PERSON_SESSION) == null){
			return WebxConstants.REDIRECT + "/";
		}
		Page<Article> page = ParamUtil.getPage(request, Article.class);
		articleService.getPage(page);
		model.addAttribute("page", page);
		return "/cms/article/edit-list.vm";
	}

}
