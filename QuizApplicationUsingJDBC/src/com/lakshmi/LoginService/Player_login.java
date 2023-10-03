package com.lakshmi.LoginService;
import com.lakshmi.PlayerActivities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.lakshmi.Utility.JdbcUtility;

public class Player_login 
{
	String pname,p_password;
	public void login()
	{
		Scanner sc=new Scanner(System.in);
		while(true) 
		{
			System.out.println("-----------------------------------------------------------");
			System.out.println("\t\tPlayer Menu");
			System.out.println("1.Login\n2.Register");
			System.out.print("Enter your Choice : ");
			char choice=sc.next().charAt(0);
			
			if(choice=='1') {
				login_service();
				break;
			}
			else if(choice=='2') {
				register_service();
				break;
			}
			else
			{
				System.out.println("Sorry invalid Choice\nPlease Select a Valid Choice");
				continue;
			}
		}
		sc.close();
	}

	private void register_service() 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\tPlayer Registration");
		
		System.out.print("Enter First-Name: ");
		String fname=sc.nextLine();
		
		System.out.print("Enter Last-Name :");
		String lname=sc.nextLine();
		
		System.out.print("Enter age : ");
		int age=Integer.parseInt(sc.next());
		
		sc.nextLine();
		System.out.print("Enter occupation :");
		String occupation=sc.nextLine();
		
		System.out.print("Enter Username: ");
		String username=sc.next();
		
		System.out.print("Enter Password :");
		String password=sc.next();
		
		String sql="insert into player_login(Firstname,Lastname,age,Occupation,p_username,p_password) "
				+ "values(?,?,?,?,?,?)";
		Connection connect=null;
		PreparedStatement pst=null;
		int result=0;
		  
		try
		{
			connect=JdbcUtility.getDbConnection();
			if(connect!=null)
			{
				pst=connect.prepareStatement(sql);
				pst.setString(1,fname);
				pst.setString(2,lname);
				pst.setInt(3,age);
				pst.setString(4,occupation);
				pst.setString(5,username);
				pst.setString(6,password);	
			}
			if(pst!=null)
			{
				result=pst.executeUpdate();
				if(result > 0)
				{
					System.out.println(username+" Account Created Successfully.........");
					login_service();
				}	
				else 
				{
					System.out.println("Error Occuered in Creating the Account....");
					register_service();
				}
			}
			 
		}
		catch(Exception e) {System.out.println(e.getMessage());}
		finally 
		{
			JdbcUtility.closeSpecialResources(pst, connect);
			sc.close();
		}
	}

	private void login_service() 
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\tPlayer Login");
		System.out.print("Enter Username: ");
		String username=sc.next();
		System.out.print("Enter Password :");
		String password=sc.next();
		
		String sql="select p_username,p_password from player_login";
		Connection connect=null;
		Statement st=null;
		ResultSet rs=null;
		int status=0;
		
		try
		{
			connect=JdbcUtility.getDbConnection();
			if(connect!=null)
			{
				st=connect.createStatement();
			}
			if(st!=null)
			{
				rs=st.executeQuery(sql);
			}
			if(rs!=null)
			{
				while(rs.next())
				{
					 pname=rs.getString("p_username");
					 p_password=rs.getString("p_password");
					if(pname.equals(username)&&p_password.equals(password))
					{
						status=1;
						break;
					}
				}
				if(status==1)
				{
					System.out.println("Valid User ....PLease Proceed");
					System.out.println("--------------------------------------------------------");
					System.out.println("\t\tWelcome "+pname.toUpperCase());
					System.out.println("You Can play Multiple Quiz");
					System.out.println("Correct Answer            -> (+2)score");
					System.out.println("Wrong Answer              -> (-1)score");
					System.out.println("You can Skip the Question -> (Score doesn't calculate)");
					System.out.println("Each Question will have 10 seconds to Answer.....");
					System.out.println("At the End of Each Quiz You will get the Score Card....");
					System.out.println("\n\t\tAll the Best..");
					System.out.println("--------------------------------------------------------");
					Thread.sleep(3000);
					PlayerMenu pm=new PlayerMenu();
					pm.menu();
				}	
				else
				{
					System.out.println("Invalid User......");
					login_service();
				}		
			}
		}
		catch(Exception e) {System.out.println(e.getMessage());}
		finally 
		{
			JdbcUtility.closeResources(rs, st, connect);
			sc.close();
		}
	}
}
