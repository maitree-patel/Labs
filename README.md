# Labs
## Lab01
1. Write code to print to the console 1,000 randomly generated DNA 3 mers (e.g. “ACA”, “TCG”) where the frequency of A,C,G and T is 25% and is uniformly sampled.
2. Have your code track how often it prints out the 3 mer (“AAA”). How often would you expect to see this 3mer by chance?  Is Java’s number close to the number that you would expect?
3. Modify your code so that the frequency of A,C,G and T is
   p(A) = 0.12
   p(C) = 0.38
   p(G) = 0.39
   p(T) = 0.11
   What is the expected frequency now of “AAA”?  Does Java produce “AAA” at close to the expected frequency?
   
   Additionally, Modify your code and test code to work on protein sequences so that random sequences are generated with the background frequency of proteins in SwissProt: https://web.expasy.org/protscale/pscale/A.A.Swiss-Prot.html
   
   How do you test your (or AI code) to make sure that protein sequences match the expected background levels?

## Lab 02
Make a quiz to drill amino acids!
- It is 30 seconds long
- The quiz ends after 30 seconds or when there is a single incorrect answer
- The program displays the full name of an amino acid (like “alanine” ) and asks the user to type in the one character code (like a)
- The quiz ignores case in the answer.
- The program displays the amino acids in random order, can repeat amino acids and can show more than 20 if the user gets that many correct.
- The total score is the number of correct answers

**Improvements/modifications**
- Make the # of seconds a parameter that can be passed in on the command line.
- Alternatively, allow an option that will ask a certain number of questions before terminating or allow the user to quit by typing “quit”
- Have the program not terminate on wrong answers but give a report at the end
summarizing which amino acids were gotten right and wrong how many times.

When I used ChatGPT, it got partway there but didn’t perfectly meet the specifications.
Compare your solution to a ChatGPT solution.  What data structures did ChatGPT use 
to solve the problem?  Does its code meet all the requirements of the specifications?

## Lab03

## Lab04
### Part 1
Make a static factory method that parses a Fasta file and returns a list 
of FastaSequence objects:

*public static List<FastaSequence> readFastaFile(String filepath) throws Exception {…}*

Your FastaSequence class should have at least 3 methods:

// returns the header of this sequence without the “>”
 
*public String getHeader() {…}*

// returns the Dna sequence of this FastaSequence
 
*public String getSequence() {…}*
	
// returns the number of G’s and C’s divided by the length of this sequence
	
*public float getGCRatio() {…}*

### Part 2
Implement the below method that inputs a List<FastaSequence> and outputs a columnar spreadsheet represented as a tab-separated .txt file

*public static void writeTableSummary( List<FastaSequence> list, File outputFile) throws Exception {}*

All fields in all rows should be tab separated (\t) with a newline (\n) at the end. The first line of your output file should be a header row with “sequenceID numA numC numG numT sequence” (all tab separated with a newline at the end).

For each sequence in the fasta file there should be one row with the sequenceID, then the number of A,C,G,T and the sequence itself (all tab separated with a newline at the end).

So the input file on the left would produce the text file on the right (that can be opened in Excel or a text editor of your choice).

<img width="1103" alt="Screenshot 2024-10-08 at 4 39 51 PM" src="https://github.com/user-attachments/assets/09e79505-87ab-459f-a536-eeabc2eb0c25">




   

