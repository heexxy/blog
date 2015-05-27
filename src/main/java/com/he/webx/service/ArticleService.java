package com.he.webx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.he.webx.mapper.ArticleMapper;
import com.he.webx.mapper.page.Page;
import com.he.webx.utils.CommonUtils;
import com.he.webx.utils.TimeUtils;
import com.he.webx.domain.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	/**
	 * @description 不分页获取全部文章
	 * @return
	 */
	public List<Article> getAll() {
		return articleMapper.queryAll();
	}

	/**
	 * @description 分页获取全部文章
	 * @param page
	 * @return
	 */
	public Page<Article> getPage(Page<Article> page) {
		return articleMapper.getPage(page);
	}

	/**
	 * @description 根据ID获取文章信息
	 * @param id
	 * @return
	 */
	public Article getArticleById(String id) {
		return articleMapper.getArticleById(id);
	}

	/**
	 * 获取当前最大ID
	 * 
	 * @return
	 */
	public String getMaxId() {
		String id = articleMapper.getArticleMaxId();
		return id;
	}

	/**
	 * @description 更新点击数
	 * @param id
	 */
	public void updateClick(String id) {
		articleMapper.updateClick(id);
	}

	/**
	 * @description 保存文章
	 * @param article
	 */
	public void insetArticle(Article article) {
		articleMapper.insertArticle(article);
	}
	
	/**
	 * @@description 获取下一篇或者上一篇文章
	 * @param id
	 * @param type
	 * @return
	 */
	public Article getNextArticle(Map map){
		return articleMapper.getNextArticle(map);
	}
	
	/**
	 * @description 根据标签获取文章列表
	 * @param tag
	 * @return
	 */
	public Page<Article> getArticleByTag(Page<Article> page,String tag){
		return articleMapper.getArticleByTag(page,tag);
	}
	
	/**
	 * @description 根据关键字搜索标题
	 * @param page
	 * @param word
	 * @return
	 */
	public Page<Article> getArticleByWord(Page<Article> page,String word){
		return articleMapper.getArticleByWord(page, word);
	} 
	
	/**
	 * @description 根据ID更新文章
	 * @param article
	 */
	public void updateArticleById(Article article){
		articleMapper.updateArticleById(article);
	}
}
