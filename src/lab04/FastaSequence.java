package lab04;

import java.util.*;
import java.io.*;
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;

//creating FastaSequence class with three methods
public class FastaSequence
{
	private String header;
	private String sequence;

	//constructor for class goes here
	public FastaSequence (String header, String sequence)
	{
		this.header = header;
		this.sequence = sequence;
	}
	
	public String getHeader()
	{
		return header;
	}
	
	public String getSequence() 
	{
		return sequence;
	}
	
	public float gcRatio() 
	{
		float total_len = sequence.length();
		float count_gc = 0;
		
		for (int i=0; i<sequence.length(); i++) 
		{
			String nucleotide = ""+sequence.charAt(i); 
			if (nucleotide == "G" || nucleotide == "C") 
			{
				count_gc += 1;
			}
		}
		
		float gc_ratio = (total_len/count_gc)*100;
		
		return gc_ratio;
	}
	
	public int getA()
	{
		int count_a = 0;
		
		for (int i=0; i<sequence.length(); i++) 
		{
			String nucleotide = ""+sequence.charAt(i); 
			if (nucleotide == "A") 
			{
				count_a += 1;
			}
		}
		return count_a;
	}
	
	public int getG()
	{
		int count_g = 0;
		
		for (int i=0; i<sequence.length(); i++) 
		{
			String nucleotide = ""+sequence.charAt(i); 
			if (nucleotide == "G") 
			{
				count_g += 1;
			}
		}
		return count_g;
	}
	
	public int getC()
	{
		int count_c = 0;
		
		for (int i=0; i<sequence.length(); i++) 
		{
			String nucleotide = ""+sequence.charAt(i); 
			if (nucleotide == "C") 
			{
				count_c += 1;
			}
		}
		return count_c;
	}
	
	public int getT()
	{
		int count_t = 0;
		
		for (int i=0; i<sequence.length(); i++) 
		{
			String nucleotide = ""+sequence.charAt(i); 
			if (nucleotide == "T") 
			{
				count_t += 1;
			}
		}
		return count_t;
	}
	
	public static List<FastaSequence> readFastaFile(String filepath) throws Exception
	{
		//read in file path and parse to get list of fasta sequence objects
		List<FastaSequence> fastaSequences = new ArrayList<>();
		BufferedReader fastafile = new BufferedReader(new FileReader("test.fasta"));
		
		//parsing reader
		
		//reading each line
		String line = fastafile.readLine();
		
		if (line.startsWith(">")) 
		{
			String header = line.substring(1);
			String sequence = line+1;
			
			//adding a new FastaSequence object
			fastaSequences.add(new FastaSequence(header, sequence));
		}
		fastafile.close();
		return fastaSequences;
	}

	// Part 2
	public static void writeTableSummary( List<FastaSequence> list, File outputFile) throws Exception 
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		
		//header
		writer.write("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence");
		
		for (FastaSequence seq : list) 
		{
			String seqid = seq.header;
			int numa = seq.getA();
			int numc = seq.getC();
			int numg = seq.getG();
			int numt = seq.getT();
			String sequence = seq.sequence;
			
			writer.write(seqid + "\t" + numa + "\t" + numc + "\t" + numg + "\t" + numt + "\t" + sequence);
		}
		
		writer.flush();
		writer.close();
	}
}

