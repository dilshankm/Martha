package com.nyleptha.martha.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XMLUtility {
	
	public Document initilizeXML(String filepath){
		Document doc = null;
		try{
			File file = new File(filepath);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = dBuilder.parse(file);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return doc;	
	}

}
