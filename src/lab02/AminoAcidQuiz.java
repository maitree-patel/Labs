package lab02;

import java.util.Random;

public class AminoAcidQuiz 
{
	
	public static void main(String[] args) 
	{
		/*first, make a random aminno acid generator (make it using an array)
		 * second, edit it to be for 30 seconds? how to use timer as a condition
		 * third, make the quiz end after 30 seconds (even if there is a wrong answer
		 * fourth, code to calculate and print the score, i.e = number of correct answers out of the total (as percentage?)
		 */
		
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
		int x = 0; //remove after changing condition!
		int total_ques = 0;
		int correct = 0;
		
		while (x<(aa_fullname.length)) //change condition!
		{
			int index = random.nextInt(20);
			
			String aa_ques = aa_fullname[index];
			String aa_ans = "" + aa_ques.charAt(0);
			
			System.out.println(aa_ans);
			System.out.println("What is the single character code for " + aa_ques + " ?");
			
			String aa_input = System.console().readLine().toUpperCase();
			
			if (aa_input.equals(aa_ans)) 
			{
				System.out.println("Right answer! Your score is now"); //Add score and time to this print
			} else 
			{
				System.out.println("Wrong answer, the right answer is " + aa_ans + "." + "Your score is now ");
			}
			x++;
			
		}
		
	}

}

