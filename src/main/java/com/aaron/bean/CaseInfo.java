package com.aaron.bean;

import java.util.LinkedList;

public class CaseInfo {

	private String classname;

	private String methodname;

	private LinkedList<CaseParam> datas;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public LinkedList<CaseParam> getDatas() {
		return datas;
	}

	public void setDatas(LinkedList<CaseParam> datas) {
		this.datas = datas;
	}

}
