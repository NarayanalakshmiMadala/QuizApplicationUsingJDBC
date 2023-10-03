package com.lakshmi.CRUD_Operations;
import java.util.Scanner;
public class Admin_Operations 
{
	public void quizname()
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
		System.out.print("Select Your Choice To view Quiz on that particular topic : ");
		char choice=sc.next().charAt(0);
		switch(choice)
		{
			case '1': show_menu("computer_basics");
				 	  break;
			case '2':show_menu("core_java"); 
				 	 break;
			case '3':show_menu("advanced_java"); 
				 	 break;
			case '4':show_menu("springboot"); 
			     	 break;
			case '5':show_menu("microservices"); 
					 break;
			
			case '6':{
					   System.out.println("--------------------------------------------------------");
					   System.out.println("Thanks for Using Quiz Application.....\nHave a Wonderful Day");
					   System.out.println("--------------------------------------------------------");
					   System.exit(0);
					  }
			 default :System.out.println("Invalid Choice\nPlease Select between (1 to 6).......");
			 		  quizname();
		}
		sc.close();
		
	}
	public void show_menu(String quiz_name) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("--------------------------------------------------------");
		System.out.println("\t\t"+quiz_name.toUpperCase()+" Quiz ");
		System.out.println("1.Create a new Question");
		System.out.println("2.Read a Question");
		System.out.println("3.Update an Existing Question");
		System.out.println("4.Delete The Question");
		System.out.println("5.Retrive all the Questions");
		System.out.println("6.Previous Menu");
		System.out.println("7.Exit");
		System.out.print("Please select Your Choice : ");
		char choice=sc.next().charAt(0);
		switch(choice)
		{
			case '1':Insertion i=new Insertion();
				 	 i.insert(quiz_name);
				 	 break;
			case '2':Retrive_Data rd=new Retrive_Data();
				 	 rd.read(quiz_name);
				 	 break;
			case '3':UpdateQuestion ur=new UpdateQuestion();
				 	 ur.update(quiz_name);
				 	 break;
			case '4':DeleteQuestion dq=new DeleteQuestion();
			     	 dq.delete(quiz_name);
			     	 break;
			case '5':RetriveAllQuestions rq=new RetriveAllQuestions();
					 rq.allQuestions(quiz_name);
					 break;
			case '6':quizname();
					 break;
			case '7':{
						System.out.println("--------------------------------------------------------");
						System.out.println("Thanks for Using Quiz Application.....\nHave a Wonderful Day");
						System.out.println("--------------------------------------------------------");
						System.exit(0);
					}
			default :System.out.println("Invalid Choice");
					show_menu(quiz_name);
		}
		System.out.println("--------------------------------------------------------");
		sc.close();
	}
}

