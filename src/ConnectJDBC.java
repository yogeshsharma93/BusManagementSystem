package busmanagementsystem;
import java.util.*;
import java.sql.*;

public class ConnectJDBC
{
	Scanner sc = new Scanner(System.in);
	Connection conn;
	Statement stmt;
	public void setconnection()
	{
		try
		{ 
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/busm","root", "yogesh");
			stmt=conn.createStatement();
			System.out.println("Database is connected !");
		}
		catch(Exception e)
		{
			System.out.print("Do not connect to DB - Error:"+e);
		}
	}
}