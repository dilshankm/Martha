package com.nyleptha.martha;

import java.util.HashMap;

import com.nyleptha.marhta.dictinorary.WordMapper;
import com.nyleptha.martha.parsing.Parser;

public class Executer {

	public static void main(String[] args) {
		String subject,verb,object;
		try {		
//			String sen="I went home";
//			String[] sourcePOS=sen.split(" ");
//			subject=sourcePOS[0];
//			verb=sourcePOS[1];
//			object=sourcePOS[2];
//			String[] targetPOS=POS.check(sourcePOS,"sinhala");
//			DOMMapping.setMappingEnglish(targetPOS);
//			XMLParser parser=new XMLParser();
//			
//			parser.createXML("rules");
//			parser.addChildElement("rules", "rule");
			Parser parser=new Parser();
			WordMapper mapper=new WordMapper();
			System.out.println("Hello");
			HashMap<String, String> wordMap=parser.parseThreeGenerator("I went home");
			mapper.mapWord(wordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
