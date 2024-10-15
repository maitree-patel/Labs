package lab02;

import java.math.BigDecimal;
import java.util.Random;
//importing scanner class that takes integer as input
import java.util.Scanner;

public class AminoAcidQuiz 
{
	public static void main(String[] args) 
	{
		//user input for time
		Scanner time= new Scanner(System.in);
        
		
		long start = System.currentTimeMillis();
		
		System.out.println("How long would you like the quiz to be: Enter time in seconds:");
		
		//handling string inputs to set time to 30 seconds
		int total_duration = 30;
        if (time.hasNextInt()) {
        	total_duration = time.nextInt();
        } else {
            System.out.println("Invalid input. Setting time to default value of 30 seconds.");
        }
		
		long end = System.currentTimeMillis() + (total_duration*1000);
		
		Random random = new Random();
		
		char aa_code[] = {'A', 'R', 'N', 'D', 
				          'C', 'Q', 'E', 'G', 
				          'H', 'I', 'L', 'K', 
				          'M', 'F', 'P', 'S', 
				          'T', 'W', 'Y', 'V'};
		
		String aa_fullname[] = {"alanine", "arginine", "asparagine", "aspartic acid", 
				                "cysteine", "glutamine", "glutamic acid", "glycine", 
				                "histidine", "isoleucine", "leucine", "lysine", 
				                "methionine", "phenylalanine", "proline", "serine", 
				                "threonine", "tryptophan", "tyrosine", "valine"};
		
		String affirmations[] = {"",
								 ", great job!", 
				                 ", amazing!", 
				                 ", you're smashing it!", 
				                 ", you've got it!"};

		int total_ques = 0;
		int correct = 0;
		int aa_score[] = new int[20]; //score for each amino acid
		int aa_total[] = new int[20]; //total number of times the amino acid is tested
		
		while (start < end) 
		{
			int index = random.nextInt(20);
			
			String aa_ques = aa_fullname[index];
			String aa_ans = ""+aa_code[index]; 
			
			System.out.println("What is the single character code for " + aa_ques + " ?");
			
			String aa_input = System.console().readLine().toUpperCase();
			total_ques += 1;
			
			if (aa_input.equals("QUIT")) 
			{
				break;
			} else if (aa_input.equals(aa_ans)) 
			{
				correct += 1;
				aa_score[index] += 1;
				aa_total[index] += 1;
				
				int affirmation_index = random.nextInt(5);
				System.out.println("Right answer" + affirmations[affirmation_index]);
			} else 
			{
				aa_total[index] += 1;
				System.out.println("Wrong answer :( The right answer is " + aa_ans + ". " + "Your score is now " + correct + "/" + total_ques);
			}
		}
		System.out.println("Times Up!");
		System.out.println("Your Scorecard:");
		for (int i=0; i<20; i++)
		{
			if (aa_total[i] != 0) 
			{
				System.out.println("Total right for " + aa_fullname[i] + " = " + aa_score[i] + "/" + aa_total[i]);
			}
		}
		
		System.out.println("Total wrong = " + (total_ques-correct));
		System.out.println("Total correct = " + correct);
		System.out.println("Percentage score = " + (correct/total_ques)*100 + "%");
		if ((correct/total_ques)*100 < 50) 
		{
			System.out.println("You've got some work to do, let's get you to a 100!");
		} else if ((correct/total_ques)*100 > 50) 
		{
			System.out.println("You could do Better!");
		} else if ((correct/total_ques)*100 > 70) 
		{
			System.out.println("Just a little more practice until you ace this quiz!");
			
		} else if ((correct/total_ques)*100 > 80) 
		{
			System.out.println("So close, yet so far!");
			
		} else if ((correct/total_ques)*100 > 90) 
		{
			System.out.println("You almost got all right!");
			
		} else if ((correct/total_ques)*100 == 100) 
		{
			System.out.println("You did Excellent! or was that fluke?");
			System.out.println("Keep up the hard work!");
		} 
	}	
	
}


