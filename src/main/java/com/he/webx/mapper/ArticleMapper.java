package com.he.webx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.he.webx.domain.Article;
import com.he.webx.mapper.page.Page;
import com.he.webx.mapper.page.PageInterceptor;

public interface ArticleMapper extends IMapper<Article> {
	public List<Article> queryAll();
	
	public Page<Article> getPage(@Param(PageInterceptor.PAGE_KEY) Page<Article> p);
	
	public Article getArticleById(String id);
	
	public void updateClick(String id);
	
	public String getArticleMaxId();
	
	public void insertArticle(Article article);
	
	public Article getNextArticle(Map map);
	
	public Page<Article> getArticleByTag(@Param(PageInterceptor.PAGE_KEY) Page<Article> p,@Param("tag")String tag);
	
	public Page<Article> getArticleByWord(@Param(PageInterceptor.PAGE_KEY) Page<Article> p,@Param("word")String word);
	
	public void updateArticleById(Article article);
}
