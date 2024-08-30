package labs;
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
		
		while (a_total+c_total+g_total+t_total<3000) 
		{	
			for (int x=0; x<1000; x++)
			{
				for (int y=0; y<3; y++) {
					int nucleotide = random.nextInt(4);
					if (nucleotide == 0 && a_total < 750) {
						seq = seq + "A";
						a_total = a_total + 1;
					}
					else if (nucleotide == 1 && c_total < 750) {
						seq = seq + "C";
						c_total = c_total + 1;
					}
					else if (nucleotide == 2 && g_total < 750) {
						seq = seq + "G";
						g_total = g_total + 1;
					}
					else if (nucleotide ==3 && t_total < 750){
						seq = seq + "T";
						t_total = t_total + 1;
					}
				}
			}
			System.out.println(seq);
			System.out.println(a_total);
			System.out.println(c_total);
			System.out.println(g_total);
			System.out.println(t_total);
		}
	}
}
		
		

