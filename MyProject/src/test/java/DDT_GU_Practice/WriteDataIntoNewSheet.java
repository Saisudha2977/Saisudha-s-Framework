package DDT_GU_Practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoNewSheet {
	public static void main(String[] args) throws IOException {
		// Step1: Open the document in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");

		// Step2: Create WorkBook
		Workbook wb = WorkbookFactory.create(fis);

		// Step3: Create new Sheet
		Sheet sheet = wb.createSheet("Sample");

		// Step4: Create new Row
		Row row = sheet.createRow(5);

		// Step5: Create new Cell
		Cell cell = row.createCell(7);

		// Step6: Set/Provide the data to be added/written
		cell.setCellValue("Selenium"); // --> It will not return anything..

		// Step7: Open the document in java write format
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");

		// Step8: Write the data into the cell
		wb.write(fos);
		System.out.println("Data is added succesfullty..");

		// Step9: Close the WorkBook
		wb.close();

	}
}
