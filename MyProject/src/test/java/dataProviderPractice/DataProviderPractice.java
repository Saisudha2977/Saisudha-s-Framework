package dataProviderPractice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/* How many data providers we want we can create and give the respective method name in the @Test.. if we do not want to change the method name again and again we can create @Test for every data provider..*/
public class DataProviderPractice
{
	@Test(dataProvider = "getData")
	public void addProductToCart(String mobileNname, int cost, String version)
	{
		System.out.println(mobileNname+"  "+cost+"  "+version);
	}
	
	@DataProvider
	public Object[][] getData()    // Since it is [][] array we have to create object/ initialization the array
	{
		
		//Here we are doing Data hard coding..so we should not to
		
		Object[][] data = new Object[4][3];
		
		data[0][0] = "samsung";
		data[0][1] =  10000;
		data[0][2] = "1.0";
		
		data[1][0] = "redmi";
		data[1][1] =  12000;
		data[1][2] = "2.0";
		
		data[2][0] = "oppo";
		data[2][1] =  13000;
		data[2][2] = "1.8";
		
		data[3][0] = "realme";
		data[3][1] =  15000;
		data[3][2] = "3.8";
		
		return data;
	}


	@Test(dataProvider = "getInfo")
	public void addProductToCart1(String mobileNname, int cost)
	{
		System.out.println(mobileNname+" - "+cost);
	}
	@DataProvider
	public Object[][] getInfo()
	{
		Object[][] data = new Object[2][2];
		
		data[0][0] = "samsung";
		data[0][1] =  10000;
		
		data[1][0] = "redmi";
		data[1][1] =  12000;
		
		return data;
	}
	
	@Test(dataProvider = "getData1")
	public void addProductToCart1(String mobileNname, int cost, double version, String manCountry, String customer, String delDate)
	{
		System.out.println(mobileNname+"  "+cost+"  "+version+"  "+manCountry+"  "+customer+"  "+delDate);
	}
	@DataProvider
	public Object[][] getData1()
	{
		Object[][] data = new Object[1][6];
		
		data[0][0] = "samsung";
		data[0][1] =  10000;
		data[0][2] =  10.5;
		data[0][3] =  "India";
		data[0][4] =  "RAM";
		data[0][5] =  "01-02-2023";
		
		return data;
	}
}
