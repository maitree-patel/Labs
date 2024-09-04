package labs;

import java.util.Random;

public class ProteinSeqGenerator {
	
	public static void main(String[] args)  {
		
		Random random = new Random();
		
		float a_total = 0;
		float r_total = 0;
		float n_total = 0;
		float d_total = 0;
		float c_total = 0;
		float q_total = 0;
		float e_total = 0;
		float g_total = 0;
		float h_total = 0;
		float i_total = 0;
		float l_total = 0;
		float k_total = 0;
		float m_total = 0;
		float f_total = 0;
		float p_total = 0;
		float s_total = 0;
		float t_total = 0;
		float w_total = 0;
		float y_total = 0;
		float v_total = 0;
		
		String pro_seq = "";
		
		//Since 3000 nucleotide DNA sequence would make a 1000 amino acid sequence, creating a sequence of 1000 amino acids
		while (pro_seq.length()<1000) 
		{	
			int nucleotide = random.nextInt(20);
			if (nucleotide == 1 && a_total < 82.5) {
				pro_seq = pro_seq + "A";
				a_total = a_total + 1;
			}
			else if (nucleotide == 1 && r_total < 55.3) {
				pro_seq = pro_seq + "R";
				r_total = r_total + 1;
			}
			else if (nucleotide == 2 && n_total < 40.5) {
				pro_seq = pro_seq + "N";
				n_total = n_total + 1;
			}
			else if (nucleotide ==3 && d_total < 54.5){
				pro_seq = pro_seq + "D";
				d_total = d_total + 1;
			}
			else if (nucleotide ==4 && c_total < 13.7){
				pro_seq = pro_seq + "C";
				c_total = c_total + 1;
			}
			else if (nucleotide ==5 && q_total < 39.3){
				pro_seq = pro_seq + "Q";
				q_total = q_total + 1;
			}
			else if (nucleotide ==6 && e_total < 67.5){
				pro_seq = pro_seq + "E";
				d_total = d_total + 1;
			}
			else if (nucleotide ==7 && g_total < 70.7){
				pro_seq = pro_seq + "G";
				g_total = g_total + 1;
			}
			else if (nucleotide ==8 && h_total < 22.7){
				pro_seq = pro_seq + "H";
				h_total = h_total + 1;
			}
			else if (nucleotide ==9 && i_total < 59.6){
				pro_seq = pro_seq + "I";
				i_total = i_total + 1;
			}
			else if (nucleotide ==10 && l_total < 96.6){
				pro_seq = pro_seq + "L";
				l_total = l_total + 1;
			}
			else if (nucleotide ==11 && k_total < 58.4){
				pro_seq = pro_seq + "K";
				k_total = k_total + 1;
			}
			else if (nucleotide ==12 && m_total < 24.2){
				pro_seq = pro_seq + "M";
				m_total = m_total + 1;
			}
			else if (nucleotide ==13 && f_total < 38.6){
				pro_seq = pro_seq + "F";
				f_total = f_total + 1;
			}
			else if (nucleotide ==14 && p_total < 47.0){
				pro_seq = pro_seq + "P";
				p_total = p_total + 1;
			}
			else if (nucleotide ==15 && s_total < 65.6){
				pro_seq = pro_seq + "S";
				s_total = s_total + 1;
			}
			else if (nucleotide ==16 && t_total < 53.4){
				pro_seq = pro_seq + "T";
				t_total = t_total + 1;
			}
			else if (nucleotide ==17 && w_total < 10.8){
				pro_seq = pro_seq + "W";
				w_total = w_total + 1;
			}
			else if (nucleotide ==18 && y_total < 29.2){
				pro_seq = pro_seq + "Y";
				y_total = y_total + 1;
			}
			else if (nucleotide ==19 && v_total < 68.7){
				pro_seq = pro_seq + "U";
				v_total = v_total + 1;
			}
			
		}
			System.out.println(pro_seq);
			System.out.println(pro_seq.length());
		}
	}

/*
 * If it was AI code, I would use the chi-square test to verify that the protein sequence is of the expected background level
 * Or check the percentage of each amino acid by printed in out 
 */


