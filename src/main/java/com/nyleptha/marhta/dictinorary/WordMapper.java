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
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if ((pairs.getValue().toString()).contains("VB")) {
				System.out.println("Verb goes here");
				String verb = verbMapper(pairs.getValue().toString(), pairs
						.getKey().toString());
				System.out.println(verb);
				sinhalaVerbMapper(verb, pairs.getValue().toString());
			} else {
				String subjType = returnSubjectOrObject(pairs.getKey()
						.toString());
				String sinhalaWord = sinhalaMapper(pairs.getKey().toString(),
						subjType);
				System.out.println(pairs.getKey() + " = " + pairs.getValue());
				System.out.println(sinhalaWord);
			}
		}
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
			System.out.println("length" + nodeLength);
			for (int i = 0; i < nodeLength; i++) {
				String word = subjList.item(i).getTextContent();
				if (verb.equals(word)) {
					System.out.println("hell");
					filterNode = subjList.item(i).getParentNode().getNodeName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterNode;
	}

	public void sinhalaVerbMapper(String engVerb, String verbType) {
		String sinhalaVerb = "";
		String sinhalaFilterVerb = "";
		String finalValue = "";
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
						System.out.println(nodeValue+finalValue);
					} else {
						finalValue = value;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
