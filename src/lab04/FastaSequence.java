package lab04;

import java.util.*;
import java.io.*;
//import java.io.BufferedReader; 
//import java.io.FileReader; 

//creating FastaSequence class with three methods
public class FastaSequence implements Comparable<FastaSequence>
{
	private String header;
	private String sequence;

	//constructor for class goes here
	public FastaSequence (String header, String sequence)
	{
		this.header = header;
		this.sequence = sequence;
	}
	
	//4 class methods
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
			if (nucleotide.equals("G") || nucleotide.equals("C")) 
			{
				count_gc += 1;
			}
		}
		
		float gc_ratio = (count_gc/total_len)*100;
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
	
	//static factory method
	public static List<FastaSequence> readFastaFile(String filepath) throws Exception
	{
		List<FastaSequence> fastaSequences = new ArrayList<>();
		BufferedReader fastafile = new BufferedReader(new FileReader(filepath));
				
		//reading each line
		String line;
		StringBuilder sequenceBuilder = new StringBuilder();
		String head = null;
		
		while ((line = fastafile.readLine()) != null) {
	        if (line.startsWith(">")) {
	        	if (head != null) {
	                fastaSequences.add(new FastaSequence(head, sequenceBuilder.toString()));
	            }
	            head = line.substring(1); // Remove the '>' character
	            sequenceBuilder = new StringBuilder(); // Start a new sequence
	        } else {
	            sequenceBuilder.append(line); //append the sequence that spans multiple lines
	        }
	    }
        
		//adding the last line
		if (head != null) {
	        fastaSequences.add(new FastaSequence(head, sequenceBuilder.toString()));
	    }
		
		fastafile.close();
		return fastaSequences;
	}

	// Part 2
	public static void writeTableSummary(List<FastaSequence> list, File outputFile) throws Exception 
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		
		//header
		writer.write("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence\n");
		
		for (FastaSequence seq : list) 
		{
			String seqid = seq.header;
			Object numa = seq.getChar().get('A');
			Object numc = seq.getChar().get('C');
			Object numg = seq.getChar().get('G');
			Object numt = seq.getChar().get('T');
			String sequence = seq.sequence;
			
			writer.write(seqid + "\t" + numa + "\t" + numc + "\t" + numg + "\t" + numt + "\t" + sequence + "\n");
		}
		writer.flush();
		writer.close();
	}
	
	public int compareTo(FastaSequence other) {
        return Float.compare(this.gcRatio(), other.gcRatio());
    }
	
	//defining multiple comparing criteria using Comparators as static methods
	public static Comparator<FastaSequence> seqAlphabetical = new Comparator<FastaSequence>() 
	{
		//compare method as a part of comparator static method
		@Override
		public int compare(FastaSequence seq1, FastaSequence seq2) 
		{
			return seq1.sequence.compareTo(seq2.sequence);
		}
	};
	
	public static Comparator<FastaSequence> headerAlphabetical = new Comparator<FastaSequence>()
	{
		
		public int compare(FastaSequence seq1, FastaSequence seq2) 
		{
			return seq1.header.compareTo(seq2.header);
		}
	};
	
	public static void main(String[] args) throws Exception 
	{
			List<FastaSequence> fastaSequences = readFastaFile("src/test.fasta");
			
			//Collections.sort(fastaSequences);
			
			for (FastaSequence fastaSequence : fastaSequences) {
                System.out.println("Header: " + fastaSequence.getHeader());
                System.out.println("Sequence: " + fastaSequence.getSequence());
                System.out.println("GC Ratio: " + fastaSequence.gcRatio());
            }
			
			writeTableSummary(fastaSequences, new File("output.txt"));
			
			fastaSequences.sort(FastaSequence.headerAlphabetical);
			
			for (FastaSequence seqs : fastaSequences) {
				System.out.println(seqs.getHeader());
			}
	}
}


