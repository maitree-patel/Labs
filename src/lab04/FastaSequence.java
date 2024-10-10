package lab04;

import java.util.*;
import java.io.*;
//import java.io.BufferedReader; 
import java.io.FileReader; 

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
	
	public HashMap getChar()
	{
		HashMap<Character, Integer> nucleotides = new HashMap<Character, Integer>();
		nucleotides.put('A', 0);
		nucleotides.put('T', 0);
		nucleotides.put('G', 0);
		nucleotides.put('C', 0);
		
		for (char nucleotide : sequence.toCharArray()) 
		{
			if (nucleotides.containsKey(nucleotide)) 
			{
				nucleotides.put(nucleotide, (nucleotides.get(nucleotide)+1));
			}
		}
		
		return nucleotides;
	}
	
	
	//factory method
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
			String sequence = "";
			
			while (!line.startsWith(">")) 
			{
				line += 1;
				sequence += line;
			}
			
			//adding a new FastaSequence object
			fastaSequences.add(new FastaSequence(header, sequence));
		}
		fastafile.close();
		return fastaSequences;
	}

	// Part 2
	public static void writeTableSummary(List<FastaSequence> list, File outputFile) throws Exception 
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		
		//header
		writer.write("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence");
		
		for (FastaSequence seq : list) 
		{
			String seqid = seq.header;
			Object numa = seq.getChar().get('A');
			Object numc = seq.getChar().get('T');
			Object numg = seq.getChar().get('G');
			Object numt = seq.getChar().get('C');
			String sequence = seq.sequence;
			
			writer.write(seqid + "\t" + numa + "\t" + numc + "\t" + numg + "\t" + numt + "\t" + sequence);
		}
		writer.flush();
		writer.close();
	}
	
	public static void main(String[] args) throws Exception 
	{
			List<FastaSequence> fastaSequences = readFastaFile("test.fasta");
			for (FastaSequence fastaSequence : fastaSequences) {
                System.out.println("Header: " + fastaSequence.getHeader());
                System.out.println("Sequence: " + fastaSequence.getSequence());
                System.out.println("GC Ratio: " + fastaSequence.gcRatio());
            }
			
			writeTableSummary(fastaSequences, new File("output.txt"));
	}
}


