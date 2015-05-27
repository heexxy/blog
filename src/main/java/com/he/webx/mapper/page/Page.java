package com.he.webx.mapper.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * <p>分页信息。</p>
 * <p>继承ArrayList是因为如果IMapper.getPage方法的返回类型是Page，而mybatis有如下判断：</p>
 * <pre>
 * if (List.class.isAssignableFrom(method.getReturnType())) {
 *    returnsList = true;//即只有返回List才执行selectList。
 * }
 */
public class Page<T> extends ArrayList<T> implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2234884252058425294L;

	/**每页显示几条*/
	private int size = 15;
	
	/**总条数*/
	private int total = 0; 
	
	/**当前页*/
	private int currentPage = 0; 
	
	/**请求地址*/
	private String requestURL;

	/**当前记录起始索引*/
	private int currentResult = 0; 

	/**存放结果集*/
	private List<T> result = new ArrayList<T>();

	/**
	 * 
	 * <p>获取结果集</p>
	 *
	 * @return
	 */
	public List<T> getResult() {
		if (result == null) {
			return new ArrayList<T>();
		}
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * <p>获取总页数</p>
	 */
	public int getTotalPage() {
		if (total % size == 0) {
			return total / size;
		}
		return total / size + 1;
	}

	/**
	 * <p>获取总条数</p>
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * <p>设置总条数</p>
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if (size == 0) {
			size = 10;
		}
		this.size = size;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getSize();
		if (currentResult < 0) {
			currentResult = 0;
		}
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	
	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	/**
	 * <p>转化joson数据为Page对象</p>
	 * @param jsonPage
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Page getPageFromJson(JSONObject jsonPage,String url){
		Page page = new Page();
		page.setSize((Integer) jsonPage.get("pageSize"));
		page.setCurrentPage((Integer) jsonPage.get("currPage"));
		page.setTotal((Integer) jsonPage.get("rowCount"));
		page.setRequestURL(url);
		return page;
	}
}