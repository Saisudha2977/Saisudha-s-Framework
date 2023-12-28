package DDT_GU_Practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	//Step1 : Open the document in Java readable format
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
	
	//Step2 : Create the workbook
	Workbook wb = WorkbookFactory.create(fis);
	
	//Step3 : Navigate to required Sheet
	Sheet sheet = wb.getSheet("Organizations");
	
	//Step4 : Navigate to required Row
	Row row = sheet.getRow(4);
	
	//Step5 : Navigate to required Cell
	Cell cell = row.getCell(3);
	
	//Step6 : Read the data inside the Cell
	String data = cell.getStringCellValue();
	System.out.println(data);
	
	//Step7 : Close the WorkBook
	wb.close();
}
}
