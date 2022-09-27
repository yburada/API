package allianz1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;  
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class example_3 {
	
	public static int rowCount(String path) throws IOException, InvalidFormatException {
		
		FileInputStream fs = new FileInputStream(path);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		
		XSSFSheet sheet = workbook.getSheet("sheet1");
		
		int row = sheet.getLastRowNum();
		
		//System.out.println("Original"+ row);
		
		return row;
	}
	
	public static int cellCount(String path, int row_num) throws InvalidFormatException, IOException {
		
		FileInputStream fs = new FileInputStream(path);
		
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
		
		XSSFSheet sheet = workbook.getSheet("sheet1");
		
		XSSFRow row = sheet.getRow(row_num);
		
		int cell = row.getLastCellNum();
		
		return cell;
	}
	
	public static String getdata(String path, int row_num,int col_num) throws IOException {
		
		FileInputStream fs = new FileInputStream(path);
		
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		
		XSSFSheet sheet = wb.getSheet("sheet1");
		
		XSSFRow row = sheet.getRow(row_num);
		
		XSSFCell cell = row.getCell(col_num);
		
		return cell.getStringCellValue();
	}

	@Test
	public void display() throws IOException, InvalidFormatException {
		String path ="C:\\Users\\yburada\\eclipse-workspace\\allianz\\src\\data\\data.xlsx";
		FileInputStream fs = new FileInputStream(path);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		
		XSSFSheet sheet = workbook.getSheet("sheet1");
		
		int row = sheet.getLastRowNum();
		
		System.out.println("RowCount "+ row );
				
		for(int i=0;i<row;i++) {
			XSSFRow r1 = sheet.getRow(i);
			int column = r1.getLastCellNum();
			for(int j=0;j<column;j++) {
				XSSFCell col = r1.getCell(j);
				//System.out.print(col.getStringCellValue()+ " ");
				switch(col.getCellType()) {
				case NUMERIC:
					System.out.print(col.getNumericCellValue()+" ");
					break;
				case STRING:
					System.out.print(col.getStringCellValue()+" ");
					break;
				default:
					System.out.print("Got "+ col.getCellType());
					
				}
			}
			System.out.println();
		}
		System.out.println(rowCount(path));
		
		System.out.println(cellCount(path,0));
		
		workbook.close();
		fs.close();
		
	}
	

}
