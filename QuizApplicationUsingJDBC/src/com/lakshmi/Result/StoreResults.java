 package com.lakshmi.Result;

public class StoreResults 
{
	private int right_ans,wrong_ans,skipped_questions,timeout_questions,total_questions,score;
	private int percentage;
	public void storeResults(int right_ans,int wrong_ans,int skipped_questions,int timeout_questions,int score)
	{
		this.right_ans=right_ans;
		this.wrong_ans=wrong_ans;
		this.skipped_questions=skipped_questions;
		this.timeout_questions=timeout_questions;
		this.score=score;
		total_questions=right_ans+wrong_ans+skipped_questions+timeout_questions;
		percentage=(int)(score*50)/total_questions;
	}
	@Override
	public String toString() {
		return    "\nRight Answers     = " + right_ans 
				+ "\nWrong Answer      = " + wrong_ans 
				+ "\nSkipped Questions = " + skipped_questions 
				+ "\nTimeout Questions = " + timeout_questions 
				+ "\nTotal Questions   = "+ total_questions 
				+ "\nScore	          = "+ score
				+ "\nPercentage	  = "+ percentage+"%";
	}	
}

