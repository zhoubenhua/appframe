/**
 * Copyright (c) 2012-2013, Michael Yang 杨福海 (www.yangfuhai.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eyunhome.appframe.common;

import com.eyunhome.appframe.db.sqlite.annotation.Id;
import com.eyunhome.appframe.db.sqlite.annotation.Property;
import com.eyunhome.appframe.db.sqlite.annotation.Transient;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @title 反射工具,对类的方法做get和set
 */
public class FieldUtils {
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Method getFieldGetMethod(Class<?> clazz, Field f) {
		String fn = f.getName();
		Method m = null;
		if(f.getType() == boolean.class){
			m = getBooleanFieldGetMethod(clazz, fn);
		}
		if(m == null ){
			m = getFieldGetMethod(clazz, fn);
		}
		return m;
	}

	public static Method getFieldGetMethod(Class<?> clazz, String fieldName) {
		String mn = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		try {
			return clazz.getDeclaredMethod(mn);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}



	public static Method getBooleanFieldGetMethod(Class<?> clazz, String fieldName) {
		String mn = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		if(isISStart(fieldName)){
			mn = fieldName;
		}
		try {
			return clazz.getDeclaredMethod(mn);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date stringToDateTime(String strDate) {
		if (strDate != null) {
			try {
				return SDF.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Method getFieldSetMethod(Class<?> clazz, Field f) {
		String fn = f.getName();
		String mn = "set" + fn.substring(0, 1).toUpperCase() + fn.substring(1);
		try {
			return clazz.getDeclaredMethod(mn, f.getType());
		} catch (NoSuchMethodException e) {
			if(f.getType() == boolean.class){
				return getBooleanFieldSetMethod(clazz, f);
			}
		}
		return null;
	}

	private static boolean isISStart(String fieldName){
		if(fieldName==null || fieldName.trim().length()==0)
			return false;
		//is开头，并且is之后第一个字母是大写 比如 isAdmin
		return fieldName.startsWith("is") && !Character.isLowerCase(fieldName.charAt(2));
	}

	public static Method getBooleanFieldSetMethod(Class<?> clazz, Field f) {
		String fn = f.getName();
		String mn = "set" + fn.substring(0, 1).toUpperCase() + fn.substring(1);
		if(isISStart(f.getName())){
			mn = "set" + fn.substring(2, 3).toUpperCase() + fn.substring(3);
		}
		try {
			return clazz.getDeclaredMethod(mn, f.getType());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 检测 字段是否已经被标注为 非数据库字段
	 * @param f
	 * @return
	 */
	public static boolean isTransient(Field f) {
		return f.getAnnotation(Transient.class) != null;
	}

	public static boolean isBaseDateType(Field field){
		Class<?> clazz = field.getType();
		return   clazz.equals(String.class) ||
				clazz.equals(Integer.class)||
				clazz.equals(Byte.class) ||
				clazz.equals(Long.class) ||
				clazz.equals(Double.class) ||
				clazz.equals(Float.class) ||
				clazz.equals(Character.class) ||
				clazz.equals(Short.class) ||
				clazz.equals(Boolean.class) ||
				clazz.equals(Date.class) ||
				clazz.equals(Date.class) ||
				clazz.equals(java.sql.Date.class) ||
				clazz.isPrimitive();
	}

	public static String getPropertyDefaultValue(Field field){
		Property property = field.getAnnotation(Property.class);
		if(property != null && property.defaultValue().trim().length() != 0){
			return property.defaultValue();
		}
		return null ;
	}

	/**
	 * 获取某个属性对应的 表的列
	 * @return
	 */
	public static String getColumnByField(Field field){
		Property property = field.getAnnotation(Property.class);
		if(property != null && property.column().trim().length() != 0){
			return property.column();
		}


		Id id = field.getAnnotation(Id.class);
		if(id!=null && id.column().trim().length()!=0)
			return id.column();

		return field.getName();
	}
}
