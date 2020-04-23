package com.thinkgem.jeesite.common.paper;

import java.io.FileInputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

public  class WordUtils {
	/**
	 * 读取word文件内容
	 * 
	 * @param path
	 * @return buffer
	 */
	public static String readWord(String path) {
		String buffer = "";
		try {
			if (path.endsWith(".doc")) {
				FileInputStream is = new FileInputStream(path);
				WordExtractor ex = new WordExtractor(is);
				buffer = ex.getText();
				is.close();
			} else if (path.endsWith("docx")) {
				OPCPackage opcPackage = POIXMLDocument.openPackage(path);
				POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
				buffer = extractor.getText();
				opcPackage.close();
			} else {
				System.out.println("此文件不是word文件！");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
