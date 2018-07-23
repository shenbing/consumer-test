package com.aaron.common;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aaron.bean.CaseInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class JSONParser {

	private Logger log = LoggerFactory.getLogger(JSONParser.class);
	public String filename;

	public JSONParser(String filename) {
		this.filename = filename;
	}

	public CaseInfo parser() {
		File file = new File(filename);
		CaseInfo caseInfo = null;
		try {
			String content = FileUtils.readFileToString(file,
					ConfigProperties.getInstance().getString("json_file_encode"));
			caseInfo = JSON.parseObject(content, new TypeReference<CaseInfo>() {
			});
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return caseInfo;
	}

}
