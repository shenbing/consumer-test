package com.aaron.bean;

import java.util.LinkedHashMap;

public class Element {

	private String classname;

	private boolean isBean;

	private LinkedHashMap<String, Object> fields;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public LinkedHashMap<String, Object> getFields() {
		return fields;
	}

	public void setFields(LinkedHashMap<String, Object> fields) {
		this.fields = fields;
	}

	public boolean getIsBean() {
		return isBean;
	}

	public void setIsBean(Boolean isBean) {
		this.isBean = isBean;
	}

}
