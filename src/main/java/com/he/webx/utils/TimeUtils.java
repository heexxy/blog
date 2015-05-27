package com.he.webx.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	/**
	 *@description 获取当前时间，timestamp格式
	 * @return
	 */
	public static Timestamp getNowTime(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * @description 获取当前时间，java.util.Date 格式
	 * @return
	 */
	public static Date getDateTime(){
		return new Date();
	}

	/**
	 * @description 根据传入的时间格式类型，返回相应的时间字符串
	 * @param format
	 * @return
	 */
	public static String getStringTimeByFormat(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
}
