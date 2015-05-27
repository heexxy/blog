package com.he.webx.utils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class CommonUtils {

	/**
	 * @description 获取32位UUID
	 * @return
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}

	/**
	 * @description 获取文章下一位ID
	 * @return
	 */
	public static String getNextID(String id) {
		IDGenerator iDGenerator = new IDGenerator();
 		return iDGenerator.getNextId(id);
	}

	public static String encodeStr(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
