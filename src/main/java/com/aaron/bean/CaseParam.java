package com.aaron.bean;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CaseParam {

	private LinkedList<Element> casedata;

	private LinkedHashMap<String, Object> asserts;

	public LinkedList<Element> getCasedata() {
		return casedata;
	}

	public void setCasedata(LinkedList<Element> casedata) {
		this.casedata = casedata;
	}

	public LinkedHashMap<String, Object> getAsserts() {
		return asserts;
	}

	public void setAsserts(LinkedHashMap<String, Object> asserts) {
		this.asserts = asserts;
	}

}
