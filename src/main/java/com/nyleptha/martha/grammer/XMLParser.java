package com.nyleptha.martha.grammer;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

	File xmlFile = new File(
			"/home/dilshan/office/Martha/src/main/resources/rules.xml");
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder;
	String objPath = "/home/dilshan/office/Martha/src/main/resources/document.ser";

	public void createXML(String root) {
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(root);
			doc.appendChild(rootElement);
			TransformXML(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Document TransformXML(Document doc) {
		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public void addChildElement(String parent, String child) {
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			Node parentNode = doc.getElementsByTagName(parent).item(0);
			Element childElement = doc.createElement(child);
			parentNode.appendChild(childElement);
			TransformXML(doc);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getString(String tagName, Element element) {
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			NodeList subList = list.item(0).getChildNodes();
			if (subList != null && subList.getLength() > 0) {
				return subList.item(0).getNodeValue();
			}
		}
		return null;
	}

}
