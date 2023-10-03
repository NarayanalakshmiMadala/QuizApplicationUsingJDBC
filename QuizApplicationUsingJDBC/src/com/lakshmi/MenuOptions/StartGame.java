package com.lakshmi.MenuOptions;
import java.util.Scanner;
import com.lakshmi.LoginService.*;
public class StartGame 
{
	public void start()
	{
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Admin");
			System.out.println("2.Player");
			System.out.print("Please Select Your Option : ");
			char ch=sc.next().charAt(0);
			if(ch=='1')
			{
				mentor();
				break;
			}
			else if(ch=='2')
			{
				player();
				break;
			}	
			else
			{
				System.out.println("Invalid Choice Please Enter a Valid Option");
				continue;
			}
		}
		sc.close();
	}
	public void mentor()
	{
		AdminLogin alogin=new AdminLogin();
		alogin.admin_login();
	}
	public void player() 
	{
		Player_login plogin=new Player_login();
		plogin.login();
	}
}

