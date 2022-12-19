package com.spicejet.genericLibraries;

import net.bytebuddy.utility.RandomString;

public class JavaLibrary {
	/**
	 * converts string to long type data
	 * @param data
	 * @return
	 */
	public long converStringToLong(String data) {
		long longData = Long.parseLong(data);
		return longData;
	}
	
	/**
	 * converts string to int type data
	 * @param data
	 * @return
	 */
	public int converStringToInt(String data) {
		int intData = Integer.parseInt(data);
		return intData;
	}
	
	/**
	 * converts string to double type data
	 * @param data
	 * @return
	 */
	public double converStringToDouble(String data) {
		double doubleData = Double.parseDouble(data);
		return doubleData;
	}
	/**
	 * this method generates random string upto length 4 charecters
	 */
	public String getRandomString() {
		String randomString = RandomString.make(4);
		return randomString;
	}
}
