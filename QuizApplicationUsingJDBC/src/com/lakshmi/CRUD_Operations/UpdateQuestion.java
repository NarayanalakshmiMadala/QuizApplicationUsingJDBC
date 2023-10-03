package com.lakshmi.CRUD_Operations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.lakshmi.Utility.JdbcUtility;

public class UpdateQuestion 
{
	private Connection connect=null;
	private PreparedStatement pst,upst=null;
	private ResultSet rs=null;
	Scanner sc=new Scanner(System.in);
	String sql_query;
	int question_id;
	
	public void update(String quiz_name)
	{
		System.out.println("Enter Question Id : ");
		Scanner sc=new Scanner(System.in);
		question_id=sc.nextInt();
		Admin_Operations mo=new Admin_Operations();
		
		try 
		{
			connect=JdbcUtility.getDbConnection();
			if(connect!=null)
			{
				sql_query="select * from "+quiz_name+" where qid=?";
				pst=connect.prepareStatement(sql_query);
				pst.setInt(1, question_id);
			}
			if(pst!=null)
			{
				rs=pst.executeQuery();
				if(!rs.next())
				{
					System.out.println("Question Not Found with ID->"+question_id+" in DataBase");
					mo.show_menu(quiz_name);
				}
				else
				{
					System.out.println("-----------------------------------------------------------------");
					sc.nextLine();
					System.out.print("Please Enter the Updated Question :");
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
					
					String updated_query="update "+quiz_name+" set qname=?,opt1=?,opt2=?,opt3=?,opt4=?,cans=? where qid=?";
					upst=connect.prepareStatement(updated_query);
					
					upst.setString(1,question);
					upst.setString(2,option1);
					upst.setString(3,option2);
					upst.setString(4,option3);
					upst.setString(5,option4);
					upst.setInt(6,correct_answer);
					upst.setInt(7,question_id);
					
					int count=upst.executeUpdate();
					/*if(status) {
						System.out.println("Question Updated into Database SuccessFully");
					}
					else
						System.out.println("Sorry Got the Error");*/
					if(count>0)
					{
						System.out.println(count +" Question Updated into Database SuccessFully");
						mo.show_menu(quiz_name);
					}
					else
					{
						System.out.println("Sorry Got the Error");
						mo.show_menu(quiz_name);	
					}		
				}
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
			try {
				if(upst!=null)
					upst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

