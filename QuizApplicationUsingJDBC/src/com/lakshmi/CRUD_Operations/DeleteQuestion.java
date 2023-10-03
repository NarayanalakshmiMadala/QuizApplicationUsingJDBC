package com.lakshmi.CRUD_Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.lakshmi.Utility.JdbcUtility;

public class DeleteQuestion 
{
	private Connection connect=null;
	private PreparedStatement pst=null;
	
	public void delete(String quiz_name)
	{
		Scanner sc=new Scanner(System.in);
		Admin_Operations mo=new Admin_Operations();
		try
		{
			connect=JdbcUtility.getDbConnection();
			if(connect!=null)
			{
				String sql="delete from "+quiz_name+" where qid=?";
				pst=connect.prepareStatement(sql);
			}
			if(pst!=null)
			{
				System.out.println("Enter Question-Id : ");
				int id=sc.nextInt();
				pst.setInt(1 , id);
				int count=pst.executeUpdate();
				if(count > 0) {
					System.out.println(count +" Question  Deleted");
					Thread.sleep(2000);
					mo.show_menu(quiz_name);
				}
				else
				{
					System.out.println("Sorry Invalid Quesions-ID");
					Thread.sleep(2000);
					mo.show_menu(quiz_name);
				}	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		finally
		{
			JdbcUtility.closeSpecialResources(pst, connect);
			sc.close();
		}
	}
}
