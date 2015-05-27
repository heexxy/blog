package com.he.webx.mapper;

import java.io.Serializable;

import org.apache.ibatis.annotations.Param;

import com.he.webx.mapper.page.Page;

import com.he.webx.mapper.page.PageInterceptor;

public interface IMapper<T> {

	T get(Serializable id);

	Page<T> getPage(@Param(PageInterceptor.PAGE_KEY) Page<T> p);
}
