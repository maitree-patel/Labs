package labs;
import java.util.Random;
public class DNAGenerator {
	public static void main(String[] args) {
		Random random = new Random();
		
		String seq = "";
		int x = 0;
		int a_total = 0;
		int c_total = 0;
		int g_total = 0;
		int t_total = 0;
		
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
		System.out.println(seq);
		System.out.println("test");
	}
}

