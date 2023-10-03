package com.lakshmi.PlayerActivities;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lakshmi.Result.*;
import com.lakshmi.Utility.JdbcUtility;
public class DisplayQuestions 
{
	private Connection connect=null;
	private Statement st=null;
	private ResultSet rs=null;
	int correct_ans,player_ans;
	int score;
	int skipped_questions,right_ans,wrong_ans,timeout_questions;
	public void displayQuestions(String quiz_name)
	{
		Scanner sc=new Scanner(System.in);
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
					System.out.println("Q-Id:"+rs.getInt(1));
					System.out.println("Q:"+rs.getString(2));
					System.out.println("1:"+rs.getString(3));
					System.out.println("2:"+rs.getString(4));
					System.out.println("3:"+rs.getString(5));
					System.out.println("4:"+rs.getString(6));
					while(true)
					{
						System.out.print("Do you Want to Answer (Y/N ): ");
						char ch=sc.next().charAt(0);
						if(ch=='y'||ch=='Y')
						{
							skipped_questions++;
							break;
						}
						else if(ch=='n'||ch=='n')
						{
							correct_ans=rs.getInt("cans");
							long start_time=System.currentTimeMillis();
					        long end=start_time+10000;	//10 seconds timer
					        
					       	System.out.print("Player Ans :");
					        player_ans=sc.nextInt();
					        
					        long playeranstime=System.currentTimeMillis();
					        
					        int timetaken=(int) ((playeranstime-start_time)/1000);
					        
					        if(playeranstime<end)
					        {
					        	if(player_ans==correct_ans)
					    		{
					    			score=score+2;
					    			System.out.println("Correct Answer...");
					    			System.out.println("Score : "+score);
					    			right_ans++;
					    		}
					    		else
					    		{
					    			System.out.println("Wrong Answer...");
					    			System.out.println("Right Option is : "+correct_ans);
					    			score=score-1;
					    			System.out.println("Score : "+score);
					    			wrong_ans++;
					    		}	
					        }
					        else 
					        {
					        	System.out.println("Time Taken To answer : "+timetaken+" seconds");
					        	timeout_questions++;
					        }
							break;
						}
						else 
						{
							System.out.println("Invalid Choice ....\nPLease Select a Valid Answer");
							continue;
						}
					}
					System.out.println("-------------------------------------------------");
				}
			}
			Thread.sleep(2000);
			StoreResults sr=new StoreResults();
			sr.storeResults(right_ans, wrong_ans, skipped_questions, timeout_questions,score);
			DisplayResult.storeHash(quiz_name,sr);
			PlayerMenu pm=new PlayerMenu();
			pm.menu();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		finally
		{
			JdbcUtility.closeResources(rs,st,connect);
			sc.close();
		}
	}
}

