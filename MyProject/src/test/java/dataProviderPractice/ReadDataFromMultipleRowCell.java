package dataProviderPractice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import GenericUtilities.ExcelFileUtility;

public class ReadDataFromMultipleRowCell
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		ExcelFileUtility eUtil = new ExcelFileUtility();
		Object[][] allOrg = eUtil.readMultipleDataFromRowAndCell("MultipleOrg");
		for(Object eachOrg:allOrg)
		{
			System.out.println(eachOrg.toString());
		}
		
	}

}
