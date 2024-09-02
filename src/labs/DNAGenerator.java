package labs;

//Question 1
import java.util.Random;
public class DNAGenerator 
{
	public static void main(String[] args) 
	{
		Random random = new Random();
		
		String seq = "";
		
		float a_total = 0;
		float c_total = 0;
		float g_total = 0;
		float t_total = 0;
		
		float aaa_counter = 0;
		
		while (a_total+c_total+g_total+t_total<3000) 
		{	
			for (int x=0; x<1000; x++)
			{
				String codon = "";
				for (int y=0; y<3; y++) {
					int nucleotide = random.nextInt(4);
					if (nucleotide == 0 && a_total < 360) {
						seq = seq + "A";
						codon = codon + "A";
						a_total = a_total + 1;;
					}
					else if (nucleotide == 1 && c_total < 1140) {
						seq = seq + "C";
						codon = codon + "C";
						c_total = c_total + 1;
					}
					else if (nucleotide == 2 && g_total < 1170) {
						seq = seq + "G";
						codon = codon + "G";
						g_total = g_total + 1;
					}
					else if (nucleotide ==3 && t_total < 330){
						seq = seq + "T";
						codon = codon + "T";
						t_total = t_total + 1;
					}
					if (codon.equals("AAA")) {
						aaa_counter += 1;
					}
				}
			}
			System.out.println(seq);
			System.out.println(a_total);
			System.out.println(c_total);
			System.out.println(g_total);
			System.out.println(t_total);
		}
		System.out.println((aaa_counter/3000)*100);
	}
}
	
/* Question 2
 * With a symmetric distribution of nucleotides in this DNA sequence,
 * The expected occurence of each nucleotide would be 1/4,
 * And that of "AAA" would be 1/4^3 = 0.015625 	
 * Java's number for the above DNA Generator is not close to 0.015625, 
 * The observed AAA frequency in the above sequence lies between ~0.3 to ~0.75	
 */

/* Question 3
 * Changing the expected frequencies produces a observed frequency of AAA between 0.16 to 0.36
 * Which is closer to the expected frequency than the symmetric distribution of A,C,G,T.
 */

