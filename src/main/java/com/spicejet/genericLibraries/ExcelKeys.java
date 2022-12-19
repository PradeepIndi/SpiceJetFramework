package com.spicejet.genericLibraries;

public enum ExcelKeys {
	SPICEJET("spicejet");
	
private String key;
	
	private ExcelKeys(String key) {
		this.key=key;
	}
	
	public String getStringExcelKey(){
		return key.toString();
	}

}
