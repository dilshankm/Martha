package com.nyleptha.martha.rules;

public class RuleUtilizer {

	public boolean active = false;
	public boolean passive = false;
	public boolean subject = false;
	public boolean verb = false;
	public boolean object = false;

	public void validate(boolean active, String activeValue, boolean passive,
			String passiveValue, boolean subject, String subjectValue,
			boolean verb, String verbValue, boolean object, String objectValue) {
		
		String activeName = "";
		String passiveName = "";
		String subjectName = "";
		String verbName = "";
		String objectName = "";
		
		if (active) {
			activeName = activeValue;
			if (subject) {
				subjectName = activeName;
			}
			if (verb) {
				verbName = verbValue;
			}
			if (object) {
				objectName = objectValue;
			}
		}
		
		if (passive) {
			passiveName = passiveValue;
			if (subject) {
				subjectName = activeName;
			}
			if (verb) {
				verbName = verbValue;
			}
			if (object) {
				objectName = objectValue;
			}
		}
	}

}
