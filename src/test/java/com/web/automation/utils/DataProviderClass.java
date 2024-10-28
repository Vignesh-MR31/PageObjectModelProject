package com.web.automation.utils;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
	static DataFormatter formatter;

	@DataProvider(name="dataProvider")
	public static Object[][] dataProviderMethod(Method met) throws Exception {

		int rows;
		int columns;
		Object[][] credentials = null;
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Users-Data.xlsx";
		FileInputStream fileInput = new FileInputStream(path);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = workBook.getSheet(sheetName(met));
		rows = sheet.getLastRowNum();
		columns = sheet.getRow(rows).getLastCellNum();
		credentials = new Object[rows][columns];
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < columns; j++) {
				XSSFCell cell = row.getCell(j);
				formatter = new DataFormatter();
				credentials[i][j] = formatter.formatCellValue(cell);
			}
		}
		return credentials;
	}
	
	
	  public static String sheetName(Method met) { String sheet="";
	  if(met.getName().equalsIgnoreCase("verifyTheItemFiltered")) {
	  sheet="Products"; } else
	  if(met.getName().equalsIgnoreCase("loginWithValidCredentials")) {
	  sheet="Valid-Users"; } else
	  if(met.getName().equalsIgnoreCase("loginWithInValidCredentials")) {
	  sheet="Invalid-Users"; } else
	  if(met.getName().equalsIgnoreCase("registerAnAccount")||
	  met.getName().equalsIgnoreCase("registerWithAlreadyRegisteredEmail")) {
	  sheet="Register-Account"; } return sheet; }
	 
}
