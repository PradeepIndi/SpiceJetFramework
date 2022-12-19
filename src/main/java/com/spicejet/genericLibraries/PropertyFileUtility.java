package com.spicejet.genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	private FileInputStream propertyFis;
	private Properties p;
	
	/**
	 * this methos cretaes fileinput stream instance
	 * @param path
	 */
	public void openPropertyFile(String path) {
		try {
			propertyFis=new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 p=new Properties();
		try {
			p.load(propertyFis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * this method useful for get the data from property file
	 * @param key
	 * @return
	 */
	public String getPropertyFileData(String key)
	{
		
		String data = p.getProperty(key);
		
		return data;
	}
	/**
	 * this method closes file input stream instance
	 */
	public void closeProperty() {
		try {
			propertyFis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
