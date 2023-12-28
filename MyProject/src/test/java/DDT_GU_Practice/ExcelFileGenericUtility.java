package DDT_GU_Practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import GenericUtilities.ExcelFileUtility;

public class ExcelFileGenericUtility 
{
public static void main(String[] args) throws EncryptedDocumentException, IOException 
   {
	//read data into excel file
	ExcelFileUtility eUtil = new ExcelFileUtility();
	String ORGNAME = eUtil.readSingleDataFromExcelFile("Contacts", 4, 3);
	System.out.println(ORGNAME);
	
	//write data into new excel file
	eUtil.writeSingleDataIntoNewSheet("hiia", 4, 2, "iiiii");
	System.out.println("Data is added to new excel sheet");
	
	//write data into existing excel file
	eUtil.writeSingleDataIntoNewSheet("hi", 4, 7, "Bye");
	System.out.println("data is added in existing excel file");
   }
}
