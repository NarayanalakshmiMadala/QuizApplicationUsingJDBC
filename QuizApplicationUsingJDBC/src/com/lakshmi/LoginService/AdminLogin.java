package com.lakshmi.LoginService;
import com.lakshmi.MenuOptions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import com.lakshmi.CRUD_Operations.*;

import com.lakshmi.Utility.JdbcUtility;
public class AdminLogin 
{
	String mname,mpassword;
	public void admin_login()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\tAdmin Login");
		System.out.println("-----------------------------------------------------------");
		System.out.print("Enter Username: ");
		String username=sc.next();
		System.out.print("Enter Password:");
		String password=sc.next();
		
		String sql="select admin_username,admin_password from admin_login";
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
					mname=rs.getString("admin_username");
					mpassword=rs.getString("admin_password");
					if(mname.equals(username)&&mpassword.equals(password))
					{
						status=1;
						break;
					}
				}
				if(status==1) {
					System.out.println("Valid User ....PLease Proceed");
					System.out.println("--------------------------------------------------------");
					System.out.println("\t\tWelcome "+mname.toUpperCase());
					System.out.println("\nAdmin Operations");
					System.out.println("..................");
					System.out.println("=>Creating a NEW Quiz\n=>Creating a new Questions\n=>Update the Existing Questions\n"+
							"=>Delete the Question\n=>Retrive all the Questions");
					System.out.println("--------------------------------------------------------");
					Thread.sleep(3000);
					Admin_Operations mo=new Admin_Operations();
					mo.quizname();
				}
				else
				{
					System.out.println("Invalid User......");
					System.out.println("-----------------------------------------------------------");
					StartGame sg=new StartGame();
					sg.start();
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());}
		finally {
			JdbcUtility.closeResources(rs, st, connect);
			sc.close();}
	}
	
}
