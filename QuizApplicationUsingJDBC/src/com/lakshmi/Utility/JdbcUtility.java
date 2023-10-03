 package com.lakshmi.Utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtility 
{
	//Load and Register The Driver
	static 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Driver Registered Successfully.....");
	}
	public static Connection getDbConnection()
	{
		//Establishing the Connection
		String url="jdbc:mysql://localhost:3306/quizapplication";
		String uname="root";
		String upassword="123456";
		try 
		{
			System.out.println("Connection Established............");
			return DriverManager.getConnection(url, uname, upassword);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Connection Established............");
		return null;
	}
	public static void closeResources(ResultSet rs,Statement st,Connection c)
	{
		try
		{
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(c!=null)
				c.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void closeSpecialResources(Statement st,Connection c)
	{
		try
		{
			if(st!=null)
				st.close();
			if(c!=null)
				c.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
