package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadExcel {
	
	public static int rowCount;
	public static XSSFSheet sheet;
	public static XSSFWorkbook book;
	public static void loadExcelSheet(){
		try{
			
		File file=new File("C:\\Users\\DELL\\Desktop\\TestData.xlsx");
		FileInputStream fis=new FileInputStream(file);
		try {
			book=new XSSFWorkbook(file);
			sheet=book.getSheet("TestSteps");
			rowCount=sheet.getLastRowNum();
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

}
