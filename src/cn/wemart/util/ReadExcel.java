package cn.wemart.util;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcel {
	
	public static String Do(int columnNo) {
		String value = "";
		try {
			FileInputStream fs = new FileInputStream("../BuyerId.xls");
			POIFSFileSystem ps = new POIFSFileSystem(fs);
			HSSFWorkbook workbook = new HSSFWorkbook(ps);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			// 遍历行
			for (int i = 1; i < rows; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Cell cell = row.getCell(columnNo);
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_FORMULA:
							break;
						case Cell.CELL_TYPE_NUMERIC:
							int valueNum = (int) cell.getNumericCellValue();
							value += String.valueOf(valueNum) + ",";
							value += cell.getNumericCellValue() + ",";
							break;
						case Cell.CELL_TYPE_STRING:
							Integer valueString = (int) Float.parseFloat(cell.getStringCellValue());
							value += String.valueOf(valueString) + ",";
//							value += cell.getStringCellValue() + ",";
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
