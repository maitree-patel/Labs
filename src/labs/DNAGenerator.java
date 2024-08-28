package labs;
import java.util.Random;
public class DNAGenerator {
	public static void main(String[] args) {
		Random random = new Random();
		
		String seq = "";
		int x = 0;
		float a_total = 0;
		float c_total = 0;
		float g_total = 0;
		float t_total = 0;
				
		while (x<1000)
		{
			for (int y=0; y<3; y++) {
				int nucleotide = random.nextInt(4);
				if (nucleotide == 0) {
					seq = seq + "A";
					a_total = a_total + 1;
				}
				else if (nucleotide == 1) {
					seq = seq + "C";
					c_total = c_total + 1;
				}
				else if (nucleotide == 2) {
					seq = seq + "G";
					g_total = g_total + 1;
				}
				else {
					seq = seq + "T";
					t_total = t_total + 1;
				}
			}
			x++;
		}
		
		float a_proportion = (a_total/3000)*100;
		float c_proportion = (c_total/3000)*100;
		float g_proportion = (g_total/3000)*100;
		float t_proportion = (t_total/3000)*100;
		
		System.out.println(seq);
	}
}

