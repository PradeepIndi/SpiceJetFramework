package com.spicejet.genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	private FileInputStream fisExcel;
	private Workbook workbook;
	private FileOutputStream fos;
	
	
	/**
	 * This method is useful for open EXcel instances
	 * @param path
	 * @param sheetName
	 */
	public void openExcel(String path) {
		try {
			fisExcel = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook= WorkbookFactory.create(fisExcel);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			fos=new FileOutputStream(path) ;
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	/**
	 * this method is useful for get the data from excel
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public String getExcelData(String sheetName,int rowIndex,int cellIndex) {
		
		Cell cell = workbook.getSheet(sheetName).getRow(rowIndex).getCell(cellIndex);
		DataFormatter df=new DataFormatter();
		String data=df.formatCellValue(cell);
		return data;
		
	}
	public void closeExcel() {
		try {
			fisExcel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			fos.close();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		catch (Exception e2) {
//			//System.out.println("not executed excel write");
//		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
