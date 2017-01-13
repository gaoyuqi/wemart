package cn.wemart.util;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcel {
	
	public static String Do(int sheetNo,int columnNo) {
		String value = "";
		try {
			FileInputStream fs = new FileInputStream("../TestCaseInfo.xls");
			POIFSFileSystem ps = new POIFSFileSystem(fs);
			HSSFWorkbook workbook = new HSSFWorkbook(ps);
			HSSFSheet sheet = workbook.getSheetAt(sheetNo);
			int rows = sheet.getPhysicalNumberOfRows();
			// 遍历行
			for (int i = 1; i < rows; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Cell cell = row.getCell(columnNo);
					if (cell != null) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_FORMULA:
							break;
						case Cell.CELL_TYPE_NUMERIC:
							Integer valueString = (int)(cell.getNumericCellValue());
							value += String.valueOf(valueString) + ",";
							break;
						case Cell.CELL_TYPE_STRING:
							value += cell.getStringCellValue() + ",";
							break;
						default:
							value += "0";
							break;
						}
					}
				
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}
}
