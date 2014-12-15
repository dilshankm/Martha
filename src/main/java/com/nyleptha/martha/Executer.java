package com.nyleptha.martha;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;

import com.nyleptha.martha.parsing.POS;
import com.nyleptha.martha.xml.DOMMapping;

public class Executer {

	public static void main(String[] args) {
		String subject,verb,object;
		try {		
			String sen="I went home";
			String[] sourcePOS=sen.split(" ");
			subject=sourcePOS[0];
			verb=sourcePOS[1];
			object=sourcePOS[2];
			String[] targetPOS=POS.check(sourcePOS,"sinhala");
			DOMMapping.setMappingEnglish(targetPOS);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
