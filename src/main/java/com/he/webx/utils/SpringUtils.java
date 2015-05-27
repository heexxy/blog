package com.he.webx.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

public final class SpringUtils extends ApplicationObjectSupport {

	private static ApplicationContext applicationContext = null;

	@Override
	protected void initApplicationContext(ApplicationContext context)

	throws BeansException {

		// TODO Auto-generated method stub

		super.initApplicationContext(context);

		if (SpringUtils.applicationContext == null) {

			SpringUtils.applicationContext = context;

		}

	}

	public static ApplicationContext getAppContext() {

		return applicationContext;

	}

	public static Object getBean(String name) {

		return getAppContext().getBean(name);

	}
}
