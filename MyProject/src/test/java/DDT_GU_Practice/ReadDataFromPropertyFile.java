package DDT_GU_Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {
public static void main(String[] args) throws IOException {
	//Step1: Open the document in Java readable format
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
	
	//Step2: Create object for Properties class-- java.util package
	Properties p = new Properties();
	
	//Step3: Load the document into the Properties class
	p.load(fis);
	
	//Step4: Provide key and read the value 
	String USERNAME = p.getProperty("username");
	String PASSWORD = p.getProperty("password");
	String BROWSER = p.getProperty("browser");
	String URL = p.getProperty("url");
	
}
}
