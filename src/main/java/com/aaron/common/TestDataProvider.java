package com.aaron.common;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aaron.bean.CaseInfo;
import com.aaron.bean.Element;
import com.aaron.utils.ClassUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;

public class TestDataProvider {

	private Logger log = LoggerFactory.getLogger(TestDataProvider.class);

	public String filename;

	public TestDataProvider(String filename) {
		this.filename = filename;
	}

	public Object[][] get() {
		CaseInfo caseInfo = new JSONParser(filename).parser();
		Object[][] result = new Object[caseInfo.getDatas().size()][];
		for (int i = 0; i < caseInfo.getDatas().size(); i++) {
			Object[] dataElement = new Object[] { caseInfo.getClassname(), caseInfo.getMethodname(),
					getParams(caseInfo.getDatas().get(i).getCasedata()), caseInfo.getDatas().get(i).getAsserts() };
			result[i] = dataElement;
		}
		log.info("共获取" + result.length + "条测试数据");
		return result;
	}

	private Object[] getParams(LinkedList<Element> casedata) {
		if (null == casedata || 0 == casedata.size()) {
			return null;
		}
		Object[] result = new Object[casedata.size()];
		for (int i = 0; i < casedata.size(); i++) {
			Element element = casedata.get(i);
			result[i] = getTreeParams(element);
		}
		return result;
	}

	private Object getTreeParams(Element element) {
		LinkedHashMap<String, Object> fields = element.getFields();
		for (String name : fields.keySet()) {
			try {
				Element subElement = JSON.parseObject(fields.get(name).toString(), new TypeReference<Element>() {
				});
				fields.put(name, getTreeParams(subElement));
			} catch (JSONException e) {
			}
		}
		return getObjectOfParam(element.getClassname(), element.getIsBean(), element.getFields());
	}

	private Object getObjectOfParam(String classname, Boolean isBean, LinkedHashMap<String, Object> fields) {
		Object result = null;
		if (StringUtils.isEmpty(classname)) {
			if (null == fields || 0 == fields.size()) {
				return result;
			}
			result = fields.values().toArray()[0];
		} else {
			if (null == fields || 0 == fields.size()) {
				result = ClassUtil.getInstanceByDefaultConstructor(classname);
			} else if (isBean) {
				result = ClassUtil.getBeanEntity(classname, fields);
			} else {
				result = ClassUtil.getEntityByConstructor(classname, getFields(fields));
			}
		}
		return result;
	}

	private Object[] getFields(LinkedHashMap<String, Object> fields) {
		return fields.values().toArray();
	}

	public static void main(String[] args) {
		TestDataProvider testDataProvider = new TestDataProvider(
				System.getProperty("user.dir") + "/src/test/java/testdata/test2.json");
	}

}
