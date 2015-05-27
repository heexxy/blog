package com.he.webx.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtils {

	private static Properties properties = null;

	private static String project_file = "project.properties";

	/**
	 * @description 根据key获取value值
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		if (properties == null) {
			load(project_file);
		}
		return properties.getProperty(key);
	}

	/**
	 * @description 根据key值设置value值
	 * @param key
	 * @param value
	 */
	public static void setValue(String key, String value) {
		if (properties == null) {
			load(project_file);
		}
		properties.setProperty(key, value);
		try {
			OutputStream output = new FileOutputStream(project_file);
			properties.store(output, "");
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description 加载properties文件
	 * @param path
	 */
	private static void load(String path) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(path);
		properties = new Properties();
		try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
