package labs;

import java.util.Random;

public class DNAGenerator {
	public static void main(String[] args) {
		Random random = new Random();
		String seq = "";
		int x = 0;
		while (x<2)
		{
			for (int y=0; y<3; y++) {
				int nucleotide = random.nextInt(4);
				if (nucleotide == 0) {
					seq = seq + "A";
				}
			}
			x++;
		}
		
	}
}
