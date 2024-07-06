package com.web.automation.basepages;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
	static DataFormatter formatter;

	@DataProvider(name="dataProvider")
	public static Object[][] dataProviderMethod(String sheetName) throws Exception {

		int rows;
		int columns;
		Object[][] credentials = null;
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Users-Data.xlsx";
		FileInputStream fileInput = new FileInputStream(path);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = workBook.getSheet(sheetName);
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
}
