package com.he.webx.ajax;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.he.webx.domain.Article;
import com.he.webx.domain.Person;
import com.he.webx.service.ArticleService;
import com.he.webx.utils.TimeUtils;
import com.he.webx.utils.constants.WebxConstants;

@Controller
@RequestMapping(value="/ajax/article")
public class ArticleAjax {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * @description 文章管理页面按钮提交方法
	 * @param session
	 * @param id
	 */
	@RequestMapping(value="/publish",method=RequestMethod.GET)
	@ResponseBody
	public void articleControl(HttpSession session,String id,String type){
		Person p = (Person) session.getAttribute(WebxConstants.PERSON_SESSION);
		Article article = articleService.getArticleById(id);
		if("publish".equals(type)){
			article.setIsPublish(WebxConstants.PUBLISH_YES);
		}
		if("undo".equals(type)){
			article.setIsPublish(WebxConstants.PUBLISH_NO);
		}
		if("top".equals(type)){
			article.setTop(WebxConstants.TOP_YES);
		}
		if("untop".equals(type)){
			article.setTop(WebxConstants.TOP_NO);
		}
		article.setEditTime(TimeUtils.getDateTime());
		article.setEditor(p.getName());
		articleService.updateArticleById(article);
	}
}
