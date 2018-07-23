package com.aaron.consumer;

import java.lang.reflect.InvocationTargetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.aaron.common.MessageInfo;
import com.aaron.common.TestDataProvider;
import com.aaron.utils.ClassUtil;
import com.codyy.rs.rpc.so.AdminUserSo;

public class DubboConsumer {

	private Logger log = LoggerFactory.getLogger(DubboConsumer.class);

	public ApplicationContext context;

	public DubboConsumer(String file) {
		if (file.indexOf(":") == -1) {
			context = new ClassPathXmlApplicationContext(file);
		} else {
			context = new FileSystemXmlApplicationContext(file);
		}
	}

	public DubboConsumer(String[] files) {
		if (0 == files.length) {
			log.error(MessageInfo.NO_DUBBOCONSUMER_FILE);
			throw new RuntimeException(MessageInfo.NO_DUBBOCONSUMER_FILE);
		}
		if (files[0].indexOf(":") == -1) {
			context = new ClassPathXmlApplicationContext(files);
		} else {
			context = new FileSystemXmlApplicationContext(files);
		}
	}

	public Object getBeanByDubbo(String classname) {
		Object resultClass = null;
		resultClass = context.getBean(ClassUtil.getClass(classname));
		return resultClass;
	}

	@SuppressWarnings("rawtypes")
	public Object invokeMethod(Object classname, Object methodname, Object params) {
		Object dubboService = getBeanByDubbo((String) classname);
		Object result = null;
		Object[] args = (Object[]) params;
		try {
			if (null == params || 0 == args.length) {
				result = dubboService.getClass().getMethod((String) methodname).invoke(dubboService);
			} else {
				Class[] paramclass = new Class[args.length];
				for (int i = 0; i < args.length; i++) {
					paramclass[i] = args[i].getClass();
				}
				result = dubboService.getClass().getMethod((String) methodname, paramclass).invoke(dubboService, args);
			}
		} catch (BeansException e) {
			log.error(MessageInfo.GET_BEAN_FAILED);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		} catch (SecurityException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Object invokeMethod(String classname, String methodname, Class[] paramclass, Object[] params) {
		Object dubboService = getBeanByDubbo(classname);
		Object result = null;
		try {
			if (null == params || 0 == params.length) {
				result = dubboService.getClass().getMethod(methodname).invoke(dubboService);
			} else {
				result = dubboService.getClass().getMethod(methodname, paramclass).invoke(dubboService, params);
			}
		} catch (BeansException e) {
			log.error(MessageInfo.GET_BEAN_FAILED);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		} catch (SecurityException e) {
			log.error(MessageInfo.INVOKE_METHOD_FAILED);
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		DubboConsumer dubboConsumer = new DubboConsumer(new String[] { "consumer.xml" });
		Object[] casedata = new TestDataProvider(System.getProperty("user.dir") + "/src/test/java/testdata/test.json")
				.get()[0];
		AdminUserSo result = (AdminUserSo) dubboConsumer.invokeMethod(casedata[0], casedata[1], casedata[2]);
		System.out.println(result.getRealName());
	}
}
