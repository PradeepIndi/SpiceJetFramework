package com.spicejet.genericLibraries;

public enum PropertyKeys {
URL("url"),BROWSER("browser"),TIMEOUTS("timeouts");
	private String key;
	
	private PropertyKeys(String key) {
		this.key=key;
	}
	
	public String getStringPropertyKey(){
		return key.toString();
	}
}
