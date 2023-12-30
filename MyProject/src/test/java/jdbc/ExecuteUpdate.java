package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class ExecuteUpdate 
{
	@Test
	public void executeUpdate() throws SQLException
	{
		Driver driver = new Driver();
		
		//Ste1: register the driver
		DriverManager.registerDriver(driver);
		
		//Step2: get the connection with database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","Saisudha@123");
		
		//Step3: issue create statement
		Statement statement = connection.createStatement();
		
		//Step4: execute a query
		String query = "insert into empinfo(name,address,id) values('rrr','hyd',1002);";
		 int result = statement.executeUpdate(query);
		 if(result==1)
		 {
			 System.out.println("data added..");
		 }
		 
		//Step5: close the connection
		 connection.close();	
	}
}
