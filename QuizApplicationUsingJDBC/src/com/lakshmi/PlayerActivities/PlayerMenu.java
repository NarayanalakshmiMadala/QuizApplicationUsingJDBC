 package com.lakshmi.PlayerActivities;
import java.util.Scanner;
import com.lakshmi.Result.*;
public class PlayerMenu 
{
	public void menu()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("--------------------------------------------------------");
		System.out.println("\t\tQuiz Names");
		System.out.println("1.Computer Basics");
		System.out.println("2.Core Java");
		System.out.println("3.Advanced Java");
		System.out.println("4.Spring Boot");
		System.out.println("5.MicroServices");
		System.out.println("6.Exit");
		System.out.print("Select Your Choice to play Quiz on that Particular Topic : ");
		char choice=sc.next().charAt(0);
		DisplayQuestions dq=new DisplayQuestions();
		switch(choice)
		{
			case '1':dq.displayQuestions("computer_basics");
				 	  break;
			case '2':dq.displayQuestions("core_java"); 
				 	 break;
			case '3':dq.displayQuestions("advanced_java"); 
				 	 break;
			case '4':dq.displayQuestions("springboot"); 
			     	 break;
			case '5':dq.displayQuestions("microservices"); 
					 break;
			case '6':{
					   DisplayResult.displayResults();
					 }
			 default :System.out.println("Invalid Choice\nPlease Select between(1 to 6 ).......");
			 			menu();
		}		
		sc.close();
	}
}

