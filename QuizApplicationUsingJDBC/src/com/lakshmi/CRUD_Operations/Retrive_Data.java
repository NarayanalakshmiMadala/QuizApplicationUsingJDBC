package com.lakshmi.CRUD_Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.lakshmi.Utility.JdbcUtility;
import java.util.Scanner;
 
public class Retrive_Data 
{
	private Connection connect=null;
	private PreparedStatement pst=null;
	private ResultSet rs=null;
	String sql;
	public void read(String quiz_name)
	{
		Scanner sc=new Scanner(System.in);
		Admin_Operations mo=new Admin_Operations();
		try
		{
			connect=JdbcUtility.getDbConnection();
			if(connect!=null)
			{
				sql="select * from "+quiz_name+" where qid=?";
				pst=connect.prepareStatement(sql);
				System.out.println("Enter Question Id : ");
				int id=sc.nextInt();
				pst.setInt(1,id);
			}
				
			if(pst!=null)
				rs=pst.executeQuery();
			if(rs.next())
			{
				System.out.println("-------------------------------------------------");
				System.out.println("Id:"+rs.getInt(1));
				System.out.println("Q:"+rs.getString(2));
				System.out.println("1:"+rs.getString(3));
				System.out.println("2:"+rs.getString(4));
				System.out.println("3:"+rs.getString(5));
				System.out.println("4:"+rs.getString(6));
				System.out.println("Correct Ans :"+rs.getInt(7));
				System.out.println("-------------------------------------------------");
				Thread.sleep(2000);
				mo.show_menu(quiz_name);	
			}
			else
			{
				System.out.println("Invalid Question Id......\nQuestion Not Found In Database");
				System.out.println("-------------------------------------------------");
				Thread.sleep(2000);
				mo.show_menu(quiz_name);	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		finally
		{
			JdbcUtility.closeResources(rs, pst, connect);
			sc.close();
		}

	}
}

