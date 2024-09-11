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



   

