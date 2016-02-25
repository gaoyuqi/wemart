package cn.wemart.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelUtil {

	/** Excel 文件要存放的位置 */
	public static String outputFile = "testResult.xls";

	public static void ExcelUtilWrite(String API,String response,int responseValue) {
		try {

			FileInputStream fs = new FileInputStream(outputFile);
			POIFSFileSystem ps = new POIFSFileSystem(fs);
			HSSFWorkbook workbook = new HSSFWorkbook(ps);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = sheet.createRow((short) sheet.getLastRowNum() + 1);
			
			HSSFCell APICell = row.createCell((short) 0);
			APICell.setCellType(HSSFCell.CELL_TYPE_STRING);
			APICell.setCellValue(API);
			
			HSSFCell responseCell = row.createCell((short) row.getLastCellNum());
			responseCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			responseCell.setCellValue(response);
			
			HSSFCell responseValueCell = row.createCell((short) row.getLastCellNum());
			responseValueCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			responseValueCell.setCellValue(responseValue);
			
			FileOutputStream fOut = new FileOutputStream(outputFile, false);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
			System.out.println("已运行 xlCreate() : " + e);
		}

	}
}
