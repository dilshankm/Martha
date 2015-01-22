package com.nyleptha.marhta.dictinorary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.nyleptha.martha.xml.XMLUtility;

public class WordMapper {

	public void mapWord(HashMap<String, String> posMap) {

		List<String> sinhalaList = new ArrayList<String>();
		Iterator it = posMap.entrySet().iterator();
		String lastVerb = "";
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			//System.out.println("hello"+pairs.getKey().toString());
			if ((pairs.getValue().toString()).contains("VB")) {
				System.out.println("Verb goes here");
				String verb = verbMapper(pairs.getValue().toString(), pairs
						.getKey().toString());
				lastVerb = sinhalaVerbMapper(verb, pairs.getValue().toString());
			} else {
				String subjType = returnSubjectOrObject(pairs.getKey()
						.toString());
				String sinhalaWord = sinhalaMapper(pairs.getKey().toString(),
						subjType);
				System.out.println("sin"+sinhalaWord);
				sinhalaList.add(sinhalaWord);
			}
		}
		System.out.println("llllllllllll"+lastVerb);
		List<String> grammerList=grammarRuler(sinhalaList.get(0));
		for (Iterator iterator = grammerList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
			
		}
		System.out.println(grammerList.size());
		String pureString="";
		if((grammerList.get(0).contains("+"))){
			pureString=grammerList.get(0).replace("+", "").trim();
			System.out.println("inside"+grammerList.get(0));
			System.out.println(pureString);
		}
		String newLast= lastVerb.replace("යා", "යෙ");
		System.out.println("last"+newLast+pureString);
	}

	public String returnSubjectOrObject(String word) {
		System.out.println(word);
		String subjType = "";
		try {
			Document doc = new XMLUtility()
					.initilizeXML("src/main/resources/English_Morpho.xml");
			NodeList subjList = doc.getElementsByTagName(word);
			subjType = ((Element) subjList.item(0))
					.getElementsByTagName("type").item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subjType;
	}

	public String sinhalaMapper(String word, String type) {
		String sinhalaWord = "";
		try {
			Document doc = new XMLUtility()
					.initilizeXML("src/main/resources/Sinhala_Morpho.xml");
			NodeList subjList = doc.getElementsByTagName(word);
			sinhalaWord = ((Element) subjList.item(0))
					.getElementsByTagName(type).item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sinhalaWord;
	}

	public String verbMapper(String tag, String verb) {
		System.out.println(tag + "" + verb);
		String sinhalaWord = "";
		String filterNode = "";
		try {
			Document doc = new XMLUtility()
					.initilizeXML("src/main/resources/English_Morpho.xml");
			NodeList subjList = doc.getElementsByTagName(tag);
			int nodeLength = subjList.getLength();
			for (int i = 0; i < nodeLength; i++) {
				String word = subjList.item(i).getTextContent();
				if (verb.equals(word)) {
					filterNode = subjList.item(i).getParentNode().getNodeName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterNode;
	}

	public String sinhalaVerbMapper(String engVerb, String verbType) {

		String sinhalaVerb = "";
		String sinhalaFilterVerb = "";
		String finalValue = "";
		String lastValue = "";

		try {
			Document doc = new XMLUtility()
					.initilizeXML("src/main/resources/Sinhala_Morpho.xml");
			NodeList verbList = doc.getElementsByTagName(engVerb);
			int verbListSize = verbList.item(0).getChildNodes().getLength();
			for (int i = 0; i < verbListSize; i++) {
				if ((verbList.item(0).getChildNodes().item(i).getNodeName())
						.equals(verbType)) {
					String value = verbList.item(0).getChildNodes().item(i)
							.getTextContent();
					if (value.contains("+")) {
						finalValue = value.replace("+", "").trim();
						String nodeValue = verbList.item(0).getAttributes()
								.getNamedItem("lemma").getNodeValue();
						lastValue = (nodeValue + finalValue);
					} else {
						lastValue = value;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastValue;
	}

	public List<String> grammarRuler(String subjVal) {
		String verbValue = "";
		String verbTag = "";
		String objectVal = "";
		String objectTag = "";
		List<String> compoList = new ArrayList<String>();
		try {
			Document doc = new XMLUtility()
					.initilizeXML("src/main/resources/rules.xml");
			doc.getDocumentElement().normalize();
			NodeList activeList = doc.getElementsByTagName("Active");
			NodeList subjList = doc.getElementsByTagName("subject");
			NodeList verbList = doc.getElementsByTagName("verb");
			NodeList objectList = doc.getElementsByTagName("object");
			System.out.println(subjList.item(0).getNodeName());
			System.out.println(verbList.getLength());
			System.out.println(objectList.getLength());
			int nodeLength = activeList.getLength();
			//System.out.println(subjList.item(0).getFirstChild());
			System.out.println("Subject"+subjList.item(0).getChildNodes().item(1).getTextContent());
			for (int i = 0; i < nodeLength; i++) {
				if (subjList.item(0).getChildNodes().item(1).getTextContent()
						.equals(subjVal)) {
					System.out.println("insideloop");
					verbValue = verbList.item(0).getChildNodes().item(1).getTextContent();
					System.out.println("verbValue"+verbValue);
					verbTag = verbList.item(i).getChildNodes().item(1).getNodeName();
					System.out.println("verbTag"+verbTag);
					objectVal = objectList.item(i).getChildNodes().item(1).getTextContent();
					objectTag = objectList.item(i).getChildNodes().item(1).getNodeName();
					compoList.add(verbValue);
					compoList.add(verbTag);
					compoList.add(objectVal);
					compoList.add(objectTag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compoList;
	}

}
