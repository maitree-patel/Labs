package labs;

import java.util.Random;

public class ProSeqGenerator_TestAICode {
	public static void main(String[] args)  {
		
		Random random = new Random();
		
		char[] aminoAcids = {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 
		                     'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
		
		float[] maxFrequencies = {82.5f, 55.3f, 40.5f, 54.5f, 13.7f, 39.3f, 67.5f, 70.7f, 
		                          22.7f, 59.6f, 96.6f, 58.4f, 24.2f, 38.6f, 47.0f, 65.6f, 
		                          53.4f, 10.8f, 29.2f, 68.7f};
		
		//observed counts to keep a track of how many of each amino acids are generated
		float[] observedCounts = new float[20];

		
		float[] currentTotals = new float[20];
		
		StringBuilder pro_seq = new StringBuilder(); 
		
		while (pro_seq.length() < 1000) {	
			int index = random.nextInt(20);
			
			if (currentTotals[index] < maxFrequencies[index]) {
				pro_seq.append(aminoAcids[index]);
				currentTotals[index]++;
				observedCounts[index]++; //increment the counts for each amino acid
			}
		}
		
		// Output the generated sequence
		System.out.println(pro_seq.toString());
		System.out.println("Sequence length: " + pro_seq.length());
		
		/*In order to calculate the observed frequency in the same way chatgpt made the code,
		 * I used chatgpt to get the coce to calculate it by using it an array and not manually by calculating and assigning to a variable for each aminoa acid (like I did for the dna generator 
		 */
		float[] observedFrequency = new float[20];
		
		for (int i=0; i < aminoAcids.length; i++) {
			observedFrequency[i] = (float) (observedCounts[i] / pro_seq.length()) * 100;
		}
		
		//Calculating the chi-square value
		float chisq = 0;
		for (int j=0; j < aminoAcids.length; j++) {
			chisq += (Math.pow((observedFrequency[j]-maxFrequencies[j]),2))/100;
		}
		
		double critical_value = 30.144;
		
		if (critical_value < chisq) {
        	System.out.println("Reject Ho, distribution differs from that described by Ho");
        } else {
        	System.out.println("Accept Ho, distribution is same as that described by Ho");
        }
		
	}
}


