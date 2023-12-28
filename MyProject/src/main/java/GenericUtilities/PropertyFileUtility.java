package GenericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of Generic/ reusable methods related to PropertyFile
 * @author Saisudha
 */
public class PropertyFileUtility
{
	
    /**
     * This Method will read data from  Property File and return the value to caller
     * @param propertyFileKey
     * @return
     * @throws IOException
     */
	public String readDataFromPropertyFile(String propertyFileKey) throws IOException
	{
		// Trt to avoid make the method as static because the class names may be too large.. use by creating object
		// If we do not return the caller can not use the data of called function..
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(propertyFileKey);
		return value;
	}
}
