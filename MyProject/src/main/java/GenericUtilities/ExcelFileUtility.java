package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * This class consists of Generic/reusable methods related to Excel File
 * @author Saisudha
 */

public class ExcelFileUtility {
	/**
	 * This method will read the single data from Excel file and return the value to caller
	 * @param orgName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readSingleDataFromExcelFile(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException 
	{
		DataFormatter df = new DataFormatter();
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = df.formatCellValue(wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
		wb.close();
		return data;
	}
	
	/**
	 * This method will read  multiple data from rows and return it to caller
	 * @param excelFilePath
	 * @param sheetName
	 * @param cellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[] readMultipleDataRowWise(String excelFilePath, String sheetName, int cellNum) throws EncryptedDocumentException, IOException
	{
		DataFormatter df = new DataFormatter();
		FileInputStream fis = new FileInputStream(excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		int lastRow = sheet.getLastRowNum();
		Object[] data = new Object[lastRow];
		for(int i =0; i<lastRow;i++)
		{
			data[i] = df.formatCellValue(sheet.getRow(i).getCell(cellNum));
		}
		return data;
	}
	
	/**
	 * This method will read multiple data from rows and columns and return it to the caller
	 * @param excelFIlePath
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
   public Object[][] readMultipleDataFromRowAndCell(String sheetName) throws EncryptedDocumentException, IOException
   {
	   DataFormatter df = new DataFormatter();
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		int lastRow = sheet.getLastRowNum();
		int lastCell = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[lastRow][lastCell];
		for(int i =0;i<lastRow;i++) // navigate through rows.. it will take last used row
		{
			for(int j =0;j<lastCell;j++) // navigate to cells ..it will take last used cell in each row
			{
				data[i][j] = df.formatCellValue(sheet.getRow(i+1).getCell(j));
			}
		}
		return data;
   }
 
	
	/**
	 * This method will write single data into new Excel sheet
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeSingleDataIntoNewSheet(String sheetName, int rowNum, int cellNum, String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fisw = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
		Workbook wb = WorkbookFactory.create(fisw);
	    wb.createSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(data);
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
	    wb.write(fos); // write is an operation.. and it is not holding any value so no need to return anything to caller
		wb.close(); //also we can use flush() 
	}
	
	/**
	 * This method will write single data into existing Excel Sheet
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeSingleDataIntoExistingExcelSheet(String sheetName, int rowNum, int cellNum, String value) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(value);
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
		wb.write(fos);
		wb.close();
	}
	
//	public void writeMultipleDataIntoNewExcelSheet(String sheetName, int rowNum, int cellNum, String data) throws EncryptedDocumentException, IOException
//	{
//		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
//		Workbook wb = WorkbookFactory.create(fis);
//		List l = new ArrayList();
//		l.add("sai");
//		l.add("ram");
//		l.add(true);
//		l.add(100000);
//		l.add(505.7353);
//		
//		for(int i=0; i<l.size();i++)
//		{
//		 wb.createSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(l.get(i).toString());
//		}
//		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx.xlsx");
//		wb.write(fos);
//		wb.close();
//		
//	}
	
}
