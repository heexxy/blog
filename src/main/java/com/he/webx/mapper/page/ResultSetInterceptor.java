package com.he.webx.mapper.page;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * <p>分页结果放入page</p>
 * @author fanzhen
 * @email  fanzhen.meng@gmail.com
 * @time   Jul 9, 2013 9:23:26 AM
 */
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class ResultSetInterceptor implements Interceptor {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object intercept(Invocation invocation) throws Throwable {
		Object obj = invocation.proceed();
		Page page = PageInterceptor.getPage();
		if (page != null) {
			page.setResult((List) obj);
			return page;
		}
		return obj;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}