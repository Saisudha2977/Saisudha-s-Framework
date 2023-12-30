package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class ExecuteQueryJDBC 
{
	@Test
	public void readDataFromDataBase() throws SQLException
	{
		
		Driver driverref = new Driver();
		//Step1 : register the driver
		DriverManager.registerDriver(driverref);
		
		//Step2 : get connection with database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root","Saisudha@123");
		
		//Step3 : issue create statement
		Statement statement = connection.createStatement();
		
		//Step4 : Execute any query
		ResultSet result = statement.executeQuery("select * from empinfo;");
		while(result.next())
		{
			String value =result.getString("name")+"\t"+result.getString("address")+"\t"+result.getString("id");
			System.out.println(value);
		}
		
		//Step5 : close the database connection
		connection.close();
	}
}
