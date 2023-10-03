package com.lakshmi.Result;
import java.util.HashMap;

public class DisplayResult 
{
	public static HashMap<String,StoreResults> scoreCard=new HashMap<>();
	public static void storeHash(String quiz_name,StoreResults sr) throws InterruptedException
	{
		scoreCard.put(quiz_name,sr);
		System.out.println("\n\t"+quiz_name.toUpperCase()+" Quiz");
		System.out.println(scoreCard.get(quiz_name));
		Thread.sleep(3000);
	}
	public static void displayResults()
	{
		System.out.println("--------------------------------------------------------");
		System.out.println("\t\tScore card");
		System.out.println("--------------------------------------------------------");
		
		for(String i:scoreCard.keySet())
		{
			System.out.println("\n\t"+i.toUpperCase()+" Quiz");
			System.out.println(scoreCard.get(i));
			System.out.println("\n");
		}
		System.out.println("--------------------------------------------------------");
		System.out.println("Thanks for Using Quiz Application.....\nHave a Wonderful Day");
		System.out.println("--------------------------------------------------------");
		System.exit(0);
	}
}
 