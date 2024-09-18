package lab02;

import java.util.*;

public class AminoAcidQuiz_ChatGPT
{
	
	/*chatgpt used hashmap data structure to store the key-value pair for the full name and single code for amino acids
	 * It also has a separate function to get a random amino acid that is called from the main program
	 * It meets all the requirements:
	 * - It takes a user input time in seconds
	 * - It does not end when user gives a wrong answer
	 * - Gives a report at the end
	 * - It ignore case
	 * - The program displays the amino acids in random order, can repeat amino
	     acids and can show more than 20 if the user gets that many correct.
	 * - The program displays the full name of an amino acid (like “alanine” ) 
		and asks the user to type in the one character code (like a)
	 */
	
	private static final Map<String, String> aminoAcidMap = new HashMap<>();
    private static final Random random = new Random();
    
    static {
        // Populate the amino acid map with full names and their one-letter codes.
        aminoAcidMap.put("alanine", "A");
        aminoAcidMap.put("arginine", "R");
        aminoAcidMap.put("asparagine", "N");
        aminoAcidMap.put("aspartic acid", "D");
        aminoAcidMap.put("cysteine", "C");
        aminoAcidMap.put("glutamine", "Q");
        aminoAcidMap.put("glutamic acid", "E");
        aminoAcidMap.put("glycine", "G");
        aminoAcidMap.put("histidine", "H");
        aminoAcidMap.put("isoleucine", "I");
        aminoAcidMap.put("leucine", "L");
        aminoAcidMap.put("lysine", "K");
        aminoAcidMap.put("methionine", "M");
        aminoAcidMap.put("phenylalanine", "F");
        aminoAcidMap.put("proline", "P");
        aminoAcidMap.put("serine", "S");
        aminoAcidMap.put("threonine", "T");
        aminoAcidMap.put("tryptophan", "W");
        aminoAcidMap.put("tyrosine", "Y");
        aminoAcidMap.put("valine", "V");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java AminoAcidQuiz <time in seconds>");
            return;
        }

        int timeLimit;
        try {
            timeLimit = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid integer for the time limit.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        long startTime = System.currentTimeMillis();
        Map<String, Integer> correctMap = new HashMap<>();
        Map<String, Integer> wrongMap = new HashMap<>();
        int totalCorrect = 0;

        while (true) {
            // Check if the time limit is exceeded
            long currentTime = System.currentTimeMillis();
            if (((currentTime - startTime) / 1000) > timeLimit) {
                System.out.println("Time's up!");
                break;
            }

            // Get a random amino acid
            String randomAminoAcid = getRandomAminoAcid();
            String correctAnswer = aminoAcidMap.get(randomAminoAcid);

            // Ask the user for the one-letter code
            System.out.print("What is the one-letter code for " + randomAminoAcid + "? ");
            String userAnswer = scanner.nextLine().trim().toUpperCase();

            // Check the answer
            if (userAnswer.equals(correctAnswer)) {
                totalCorrect++;
                System.out.println("Correct!");

                // Update correct count for this amino acid
                correctMap.put(randomAminoAcid, correctMap.getOrDefault(randomAminoAcid, 0) + 1);
            } else {
                System.out.println("Incorrect. The correct answer was " + correctAnswer + ".");

                // Update wrong count for this amino acid
                wrongMap.put(randomAminoAcid, wrongMap.getOrDefault(randomAminoAcid, 0) + 1);
            }
        }

        // Quiz summary
        System.out.println("\nQuiz over! Your total correct answers: " + totalCorrect);
        System.out.println("\nSummary of answers:");

        System.out.println("\nCorrect Answers:");
        if (correctMap.isEmpty()) {
            System.out.println("None");
        } else {
            for (String aminoAcid : correctMap.keySet()) {
                System.out.println(aminoAcid + ": " + correctMap.get(aminoAcid) + " correct");
            }
        }

        System.out.println("\nIncorrect Answers:");
        if (wrongMap.isEmpty()) {
            System.out.println("None");
        } else {
            for (String aminoAcid : wrongMap.keySet()) {
                System.out.println(aminoAcid + ": " + wrongMap.get(aminoAcid) + " incorrect");
            }
        }
    }

    private static String getRandomAminoAcid() {
        List<String> keys = new ArrayList<>(aminoAcidMap.keySet());
        return keys.get(random.nextInt(keys.size()));
    }
}
