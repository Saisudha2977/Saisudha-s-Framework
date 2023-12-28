package DDT_GU_Practice;

import org.testng.annotations.Test;

public class readDataFromCmdLine 
{
	/*  Instead of reading the data form property file we are reading the data from system/command line during RUN Time*/
	
	@Test
	public void readDataFromCmd()
	{
		String BROWSER = System.getProperty("browser");  //here browser,url, username, password is key
		String URL = System.getProperty("url");
		String PASSWORD = System.getProperty("password");
		String USERNAME = System.getProperty("username");

		
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.err.println(PASSWORD);
		
	}

}
