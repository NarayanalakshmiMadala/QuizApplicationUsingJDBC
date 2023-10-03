package com.lakshmi.CRUD_Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.lakshmi.Utility.JdbcUtility;

public class Insertion 
{
	private Connection connect=null;
	private PreparedStatement pst=null;
	Scanner sc=new Scanner(System.in);
	int count=0;
	public void insert(String quiz_name)
	{
		Admin_Operations mo=new Admin_Operations();
		try
		{
			connect=JdbcUtility.getDbConnection();
			if(connect!=null)
			{
				String sql="insert into "+quiz_name+"(qname,opt1,opt2,opt3,opt4,cans) "
						+ "values(?,?,?,?,?,?)";
				pst=connect.prepareStatement(sql);
			}
			if(pst!=null)
			{
				System.out.println("----------------------------------------------------------------------");
				System.out.print("Please Enter the Question :");
				String question=sc.nextLine();
				System.out.print("Enter Option-1 : ");
				String option1=sc.nextLine();
				System.out.print("Enter Option-2 : ");
				String option2=sc.nextLine();
				System.out.print("Enter Option-3 : ");
				String option3=sc.nextLine();
				System.out.print("Enter Option-4 : ");
				String option4=sc.nextLine();
				System.out.print("Enter Right-Answer(1-4) :");
				int correct_answer=Integer.parseInt(sc.next());
				
				pst.setString(1,question);
				pst.setString(2,option1);
				pst.setString(3,option2);
				pst.setString(4,option3);
				pst.setString(5,option4);
				pst.setInt(6,correct_answer);
					 
				count=pst.executeUpdate();
				if(count>0)
				{
					System.out.println("Questions Inserted into Database SuccessFully");
					Thread.sleep(2000);
					mo.show_menu(quiz_name);
				}	
				else 
				{
					System.out.println("Sorry Got the Error");
					Thread.sleep(2000);
					mo.show_menu(quiz_name);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();}	
		finally
		{
			JdbcUtility.closeSpecialResources(pst, connect);
		}
	}
}
