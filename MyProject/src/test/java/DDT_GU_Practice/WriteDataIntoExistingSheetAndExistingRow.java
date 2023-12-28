package DDT_GU_Practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExistingSheetAndExistingRow {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	//Step1: Open the document in java readable document
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
	
	//Step2: Create a WorkBook
	 Workbook wb = WorkbookFactory.create(fis);
	 
	//Step3: Navigate to required sheet
    Sheet sh = wb.getSheet("Organizations");
    
	//Step4: Navigate to required row
    Row row = sh.getRow(2);
    // If we want to create the new row the use CreateRow()---------->> To create use create sheet/row/cell method .... to  get the existing sheet/ row/ cell use getSheet/row/cell methods..
    
	//Step5: Create a Cell
    Cell cell = row.createCell(7);
    
	//Step6: Set/Provide the value to be written
    cell.setCellValue("JAVA");   // this method will not return any thing
    
	//Step7: Open the document in java write format
    FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
    
	//Step8: Write the data into the cell
    wb.write(fos);
    System.out.println("Data added sucessfully");
    
	//Step9: Close the workbook
    wb.close();
	
}
}
