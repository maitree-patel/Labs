package lab06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class FindPrimesGUI {

    // Declare GUI components
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private JTextField threadField;
    private JButton startButton;
    private JButton cancelButton;

    // Volatile boolean to track cancellation
    private volatile boolean cancel = false;

    public FindPrimesGUI() {
        // Initialize the GUI components
        frame = new JFrame("Multi-threaded Prime Number Counter");
        textArea = new JTextArea(15, 40);
        inputField = new JTextField(10); // Input field for user to enter a large number
        threadField = new JTextField(5); // Input field for user to specify thread count
        startButton = new JButton("Start Counting");
        cancelButton = new JButton("Cancel");

        // Set up text area properties
        textArea.setEditable(false); // Make the text area read-only
        JScrollPane scrollPane = new JScrollPane(textArea); // Add scrolling

        // Set up input panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter a number:"));
        inputPanel.add(inputField);
        inputPanel.add(new JLabel("Number of Threads:"));
        inputPanel.add(threadField);
        inputPanel.add(startButton);
        inputPanel.add(cancelButton);
        cancelButton.setEnabled(false); // Cancel button disabled initially

        // Set up the frame layout
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set up button listeners
        setupButtonListeners();

        // Final frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private void setupButtonListeners() {
        // Start button listener
        startButton.addActionListener(e -> {
            // Get user input and validate it
            String inputText = inputField.getText();
            String threadText = threadField.getText();

            try {
                int maxNumber = Integer.parseInt(inputText);
                int numThreads = Integer.parseInt(threadText);

                if (maxNumber < 2) {
                    textArea.append("Please enter a number greater than or equal to 2.\n");
                    return;
                }

                if (numThreads < 1) {
                    textArea.append("Please enter at least 1 thread.\n");
                    return;
                }

                // Reset cancel flag and UI states
                cancel = false;
                startButton.setEnabled(false);
                cancelButton.setEnabled(true);
                textArea.append("Starting prime number calculation up to " + maxNumber + " with " + numThreads + " threads...\n");

                // Start the prime counting threads
                Thread primeThread = new Thread(() -> countPrimes(maxNumber, numThreads));
                primeThread.start();
            } catch (NumberFormatException ex) {
                textArea.append("Invalid input. Please enter valid numbers.\n");
            }
        });

        // Cancel button listener
        cancelButton.addActionListener(e -> {
            cancel = true; // Signal the threads to stop
            textArea.append("Calculation canceled by user.\n");
        });
    }

    private void countPrimes(int maxNumber, int numThreads) {
        long startTime = System.currentTimeMillis(); // Start timing

        // Divide the work among the threads
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Integer>> futures = new ArrayList<>();

        int range = maxNumber / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = i * range + 1;
            int end = (i == numThreads - 1) ? maxNumber : (i + 1) * range;
            futures.add(executor.submit(new PrimeCounterTask(start, end)));
        }

        int totalPrimes = 0;

        try {
            for (Future<Integer> future : futures) {
                if (cancel) break; // If cancel is requested, stop the process
                totalPrimes += future.get(); // Collect results from each thread
            }
        } catch (Exception e) {
            textArea.append("Error during calculation: " + e.getMessage() + "\n");
        } finally {
            executor.shutdownNow(); // Ensure the executor is shut down
        }

        if (!cancel) {
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
            textArea.append("Calculation complete! Total primes: " + totalPrimes + "\n");
            textArea.append("Time taken: " + timeTaken + " ms\n");
        }

        // Re-enable buttons on completion or cancellation
        SwingUtilities.invokeLater(() -> {
            startButton.setEnabled(true);
            cancelButton.setEnabled(false);
        });
    }

    private class PrimeCounterTask implements Callable<Integer> {
        private final int start;
        private final int end;

        public PrimeCounterTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            int primeCount = 0;
            for (int i = start; i <= end && !cancel; i++) {
                if (isPrime(i)) {
                    primeCount++;
                }
            }
            return primeCount;
        }

        private boolean isPrime(int num) {
            if (num < 2) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        // Launch the application
        SwingUtilities.invokeLater(FindPrimesGUI::new);
    }
}
