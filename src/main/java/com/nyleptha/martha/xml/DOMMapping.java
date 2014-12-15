package com.nyleptha.martha.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpression;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DOMMapping {
	
	
	public static	 void setMappingEnglish(String[] input){
		try{
			File englishMor = new File("src/main/resources/English_Morpho.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document docEn = dBuilder.parse(englishMor);
			docEn.getDocumentElement().normalize();
			
			NodeList subj = docEn.getElementsByTagName(input[0]);
			String subjType=((Element)subj.item(0)).getElementsByTagName("type").item(0).getTextContent();
//			
//			NodeList verb = docEn.getElementsByTagName(input[2]);
			NodeList obj = docEn.getElementsByTagName(input[1]);
			String objType=((Element)obj.item(0)).getElementsByTagName("type").item(0).getTextContent();
			
			NodeList verbList = docEn.getElementsByTagName("verb");
			for (int i = 0; i < verbList.getLength(); i++) {
				NodeList verbInsideList=(verbList.item(i).getChildNodes());
//				for (int j = 1; j < verbInsideList.getLength(); j++) {
					//System.out.println(verbInsideList.item(1).getChildNodes().item(1).getNamespaceURI());
					//String verb=((Element)verbInsideList.item(0)).getElementsByTagName("present").item(0).getTextContent();
//					if(verb.equals(input[2])){
//						System.out.println("Hello");
//					}
//				}
			}
//			String verb=input[2];
//			node.getParentNode().getNodeName();
			
			setMappingSinhala(input[0],subjType,input[1],objType);

		}
		catch(Exception e){
			
		}

	}
	
	public static void setMappingSinhala(String subject,String subjType,String object,String objType){
		try{
			
			File sinhalaMor = new File("src/main/resources/Sinhala_Morpho.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document docSin = dBuilder.parse(sinhalaMor);
			docSin.getDocumentElement().normalize();
			
			NodeList subj = docSin.getElementsByTagName(subject);
			String subjSin=((Element)subj.item(0)).getElementsByTagName(subjType).item(0).getTextContent();
			
			NodeList obj = docSin.getElementsByTagName(object);
			String objSin=((Element)obj.item(0)).getElementsByTagName(objType).item(0).getTextContent();
			System.out.println(subjSin+" "+objSin+" ");
//			String subjType=((Element)subj.item(0)).getElementsByTagName("type").item(0).getTextContent();
//			NodeList obj = docEn.getElementsByTagName(input[1]);
//			NodeList verb = docEn.getElementsByTagName(input[2]);

		}
		catch(Exception e){
			
		}

	}

}
