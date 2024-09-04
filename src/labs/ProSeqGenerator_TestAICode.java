package labs;

import java.util.Random;

public class ProSeqGenerator_TestAICode {
	public static void main(String[] args)  {
		
		Random random = new Random();
		
		// Array of amino acid letters
		char[] aminoAcids = {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 
		                     'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
		
		// Array of maximum frequencies for each amino acid
		float[] maxFrequencies = {82.5f, 55.3f, 40.5f, 54.5f, 13.7f, 39.3f, 67.5f, 70.7f, 
		                          22.7f, 59.6f, 96.6f, 58.4f, 24.2f, 38.6f, 47.0f, 65.6f, 
		                          53.4f, 10.8f, 29.2f, 68.7f};
		
		// Array to keep track of current totals for each amino acid
		float[] currentTotals = new float[20];
		
		StringBuilder pro_seq = new StringBuilder();  // Using StringBuilder for efficiency in appending strings
		
		// Generate a sequence of 1000 amino acids
		while (pro_seq.length() < 1000) {	
			int index = random.nextInt(20);  // Randomly pick an index from 0 to 19
			
			// Check if the current total for the chosen amino acid is below its limit
			if (currentTotals[index] < maxFrequencies[index]) {
				pro_seq.append(aminoAcids[index]);  // Append the corresponding amino acid
				currentTotals[index]++;  // Increment its total count
			}
		}
		
		// Output the generated sequence
		System.out.println(pro_seq.toString());
		System.out.println("Sequence length: " + pro_seq.length());
	}
}
