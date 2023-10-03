package com.lakshmi.CRUD_Operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lakshmi.Utility.JdbcUtility;

public class RetriveAllQuestions 
{
	private Connection connect=null;
	private Statement st=null;
	private ResultSet rs=null;
	
	public void allQuestions(String quiz_name)
	{
		try
		{
			connect=JdbcUtility.getDbConnection();
			if(connect!=null)
				st=connect.createStatement();
			if(st!=null)
				rs=st.executeQuery("select qid,qname,opt1,opt2,opt3,opt4,cans from "+quiz_name);
			if(rs!=null)
			{
				while(rs.next())
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
				}
			}
			Thread.sleep(2000);
			Admin_Operations mo=new Admin_Operations();
			mo.show_menu(quiz_name);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		finally
		{
			JdbcUtility.closeResources(rs, st, connect);
		}

	}
}
