package GenericUtilities;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class consists of re usable methods related to Java
 * @author Saisudha
 */
public class JavaUtility 
{
	/**
	 * This method will return a random number for every execution to the caller
	 * @return
	 */
	public int getRandomNumber()
	{
		Random r = new Random();
//		int num = r.nextInt(10000);
//		return num;
		return r.nextInt(1000); // directly returning the value not storing
	}
	
	
	/**
	 * This method will generate the current system data in the specified format
	 * @return
	 */
	public String getSystemDateInFormat()
	{
		Date date = new Date();   //import java.util not sql
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");  //specify u r required data format..  month should be in Caps
		String datee = formatter.format(date);
		return datee;
	}
 
}
