package com.he.webx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.he.webx.mapper.page.Page;

public class ParamUtil {
	
	public static Integer pageCount = Integer.valueOf(PropertiesUtils.getValue("pageCount"));
	
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals(key)) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}
	
	public static boolean hasIP(HttpSession session, HttpServletRequest request, String key) {
		if(ValidUtils.isNull(session.getAttribute(request.getRemoteAddr() + key))) {
			session.setAttribute(request.getRemoteAddr() + key, "true");
			return false;
		} else {
			return true;
		}
	}
	
	public static String getSearchIPAndKeyword(HttpSession session, HttpServletRequest request, String keyword) {
		StringTokenizer st = new StringTokenizer(keyword);
		String result = "";
		while (st.hasMoreTokens()) {
			String kw = st.nextToken();
			if (ValidUtils.isNull(session.getAttribute(request.getRemoteAddr() + kw))) {
				session.setAttribute(request.getRemoteAddr() + kw, "true");
			}
			result += (" " + kw);
		}
		return result.substring(1);
	}
	
	public static Date get(HttpServletRequest request, String year,
			String month, String day, Date defaultValue) {
		year = get(request, year, "");
		month = get(request, month, "");
		day = get(request, day, "");

		StringBuffer format = new StringBuffer();
		if (ValidUtils.isNotNull(year)) {
			format.append("yyyy");
		}
		if (ValidUtils.isNotNull(month)) {
			format.append("MM");
		}
		if (ValidUtils.isNotNull(day)) {
			format.append("dd");
		}
		
		if(format.toString().equals(StringPool.BLANK)) {
			return defaultValue;
		}
		
		SimpleDateFormat formater = new SimpleDateFormat(format.toString());

		try {
			return formater.parse(year + month + day);
		} catch (ParseException e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
	
	public static <T> Page<T> getPage(HttpServletRequest request,Class<T> c) {
		Page<T> page = new Page<T>();
		page.setCurrentPage(get(request, "page", 1));
		page.setSize(pageCount);
		page.setRequestURL(getRequestURL(request));
		return page;
	}
	
	public static Map<String,String> getPage(HttpServletRequest request) {
		Map<String,String> page  =  new HashMap<String, String>();
		page.put("page.currPage", request.getParameter("page")!=null?request.getParameter("page"):"1");
		page.put("page.pageSize", request.getParameter("limit")!=null?request.getParameter("limit"):pageCount.toString());
		page.put("page.requestURL", getRequestURL(request));
		return page;
	}
	
	public static <T> Page<T> getAjaxPage(HttpServletRequest request,Class<T> c) {
		Page<T> page = new Page<T>();
		page.setCurrentPage(get(request, "page", 1));
		page.setSize(15);
		page.setRequestURL(get(request, "requestURL", ""));
		return page;
	}
	
	public static String getRequestURL(HttpServletRequest request) {
		String queryString = request.getQueryString();
		StringBuffer sb = new StringBuffer();
		sb.append(request.getRequestURI());
		sb.append("?");
		if(ValidUtils.isNotNull(queryString)) {
			sb.append(queryString.replaceAll("&?page=\\d+", ""));
		}
		return sb.toString();
	}

	public static boolean get(HttpServletRequest request, String param,
			boolean defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	public static double get(HttpServletRequest request, String param,
			double defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	public static float get(HttpServletRequest request, String param,
			float defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	public static int get(HttpServletRequest request, String param,
			int defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	public static long get(HttpServletRequest request, String param,
			long defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	public static short get(HttpServletRequest request, String param,
			short defaultValue) {

		return GetterUtil.get(request.getParameter(param), defaultValue);
	}

	public static String get(HttpServletRequest request, String param,
			String defaultValue) {

		if(param == null) {
			return defaultValue;
		}
		
		return GetterUtil.get(request.getParameter(param), defaultValue);
	}
	
	public static String[] get(HttpServletRequest request, String param,
			String[] defaultValue) {
		String[] returnValues = request.getParameterValues(param);
		if(returnValues == null) {
			return defaultValue;
		}
		return returnValues;
	}
	
	public static String getSplice(HttpServletRequest request, String param, String defaultValue) {
		return GetterUtil.get(get(request, param, new String[]{defaultValue}), defaultValue);
	}
	
	
	public static String getSplice4Analizer(String from) {
		return from==null ? "" : from.replaceAll(",", " ");
	}
}
