package com.he.webx.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>反射工具类</p>
 * @author fanzhen
 * @email  fanzhen.meng@gmail.com
 * @time   Jul 9, 2013 9:20:41 AM
 */
public class ReflectUtil {
	/**
	 * <p>获取obj对象fieldName的Field</p>
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * <p>获取obj对象fieldName的属性<p>
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * <p>设置obj对象fieldName的属性</p>
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	/**
	 * @author:jiangxiucai@gmail.com
	 * 本方法用于将属性全部为string的对象属性转存到map中 ，方便接口的调用。
	 * @param t
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> Map<String, String> fillMapWithBean(T t) throws IllegalArgumentException, IllegalAccessException {
		
		Map<String, String> map = new HashMap<String, String>();
		for (Field field : t.getClass().getDeclaredFields()) {
			if (field.isAccessible()) {
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				if (field.getType().getSimpleName().equals("String")) {
					map.put(field.getName(), ((String) field.get(t)).trim());
				}
				
			}else {
				field.setAccessible(true);
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				if (field.getType().getSimpleName().equals("String")) {
					//map.put(field.getName(), ((String) field.get(t)).trim());
					map.put(field.getName(), ((String) field.get(t))!=null ? ((String) field.get(t)).trim(): null);
					//System.out.println(field.getName());
					//System.out.println(field.get(t));
				}
			}
		}
		return map;
	}
	


}