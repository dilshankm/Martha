package com.nyleptha.martha.parsing;


public class POS {
	
	private static String[] engPre={"Subject","Object","Verb"};
	private static String[] sinPre={"Subject","Verb","Object"};
	
	String targetPos;
	
	public static String[] check(String[] tagetPOS,String language){
		String[] targetPre=new String[3];
		if (language.equals("sinhala")) {
			targetPre[0]=tagetPOS[0];
			targetPre[1]=tagetPOS[2];
			targetPre[2]=tagetPOS[1];		
		}
		return targetPre;
	}

}
