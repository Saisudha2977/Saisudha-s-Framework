package dataProviderPractice;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;

import GenericUtilities.ExcelFileUtility;

public class ReadDataFromSingleRowOnly 
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		ExcelFileUtility eUtil = new ExcelFileUtility();
	   Object[] allData = eUtil.readMultipleDataRowWise(".\\src\\test\\resources\\TestData.xlsx.xlsx", "Multiple_Row", 0);
	   for(Object eachData:allData)
	   {
		   System.out.println(eachData);
	   }
		
	}

}
