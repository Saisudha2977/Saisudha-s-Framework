package DDT_GU_Practice;

import java.io.IOException;

import GenericUtilities.PropertyFileUtility;

public class PropertyFileGenericMethod 
{
	public static void main(String[] args) throws IOException 
	{
		PropertyFileUtility pUtil = new PropertyFileUtility();
		String URL = pUtil.readDataFromPropertyFile("url");
		System.out.println(URL);
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		System.out.println(BROWSER);
	}
}