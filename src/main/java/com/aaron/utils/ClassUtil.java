package com.aaron.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aaron.common.MessageInfo;

public class ClassUtil {

	private static Logger log = LoggerFactory.getLogger(ClassUtil.class);

	public static Class<?> getClass(String classname) {
		Class<?> resultClass = null;
		try {
			resultClass = ClassLoader.getSystemClassLoader().loadClass(classname);
		} catch (ClassNotFoundException e) {
			log.error(MessageInfo.NO_CLASS_FOUND + ":" + classname);
			throw new RuntimeException(MessageInfo.NO_CLASS_FOUND + ":" + classname, e);
		}
		return resultClass;
	}

	public static Object getInstanceByDefaultConstructor(String classname) {
		Object result = null;
		try {
			Class<?> resultClass = ClassLoader.getSystemClassLoader().loadClass(classname);
			result = resultClass.newInstance();
		} catch (ClassNotFoundException e) {
			log.error(MessageInfo.NO_CLASS_FOUND + ":" + classname);
			throw new RuntimeException(MessageInfo.NO_CLASS_FOUND + ":" + classname, e);
		} catch (InstantiationException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Object getBeanEntity(String classname, HashMap<String, Object> fields) {
		Class<?> clazz = getClass(classname);
		Object[] fieldsKeys = fields.keySet().toArray();
		Object object = null;
		try {
			object = clazz.newInstance();
			Field[] declaredFields = clazz.getDeclaredFields();
			HashMap<String, Class> fieldsMap = new HashMap<>();
			for (Field field : declaredFields) {
				fieldsMap.put(field.getName(), field.getType());
			}
			for (Object fieldsKey : fieldsKeys) {
				Method method = object.getClass().getDeclaredMethod("set" + toUpperCaseFirstOne((String) fieldsKey),
						fieldsMap.get(fieldsKey));
				method.invoke(object, fields.get(fieldsKey));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return object;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object getEntityByConstructor(String classname, Object[] fields) {
		Class<?> clazz = getClass(classname);
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for (Constructor constructor : constructors) {
			Class[] paramTypes = constructor.getParameterTypes();
			if (paramTypes.length == fields.length) {
				try {
					return constructor.newInstance(fields);
				} catch (Exception e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

}
