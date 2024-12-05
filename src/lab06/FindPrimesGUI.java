package lab06;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class FindPrimesGUI {

    //GUI components
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private JTextField threadField;
    private JButton startButton;
    private JButton cancelButton;
    private volatile boolean cancel = false;

    public FindPrimesGUI() {
        //initializing components
        frame = new JFrame("Count Primes GUI");
        textArea = new JTextArea(15, 40);
        inputField = new JTextField(10); //large number input 
        threadField = new JTextField(5); //no.of threads input
        startButton = new JButton("Start Counting");
        cancelButton = new JButton("Cancel");

        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter a number:"));
        inputPanel.add(inputField);
        inputPanel.add(new JLabel("Number of Threads:"));
        inputPanel.add(threadField);
        inputPanel.add(startButton);
        inputPanel.add(cancelButton);
        cancelButton.setEnabled(false);

        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        setupButtonListeners();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private void setupButtonListeners() {
        startButton.addActionListener(e -> {
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

                cancel = false;
                startButton.setEnabled(false);
                cancelButton.setEnabled(true);
                textArea.append("Starting prime number calculation up to " + maxNumber + " with " + numThreads + " threads...\n");

                Thread primeThread = new Thread(() -> countPrimesWithProgress(maxNumber, numThreads));
                primeThread.start();
            } catch (NumberFormatException ex) {
                textArea.append("Invalid input. Please enter valid numbers.\n");
            }
        });

        cancelButton.addActionListener(e -> {
            cancel = true; 
            textArea.append("Calculation canceled!\n");
        });
    }

    private void countPrimesWithProgress(int maxNumber, int numThreads) {
        long startTime = System.currentTimeMillis();
        AtomicInteger totalPrimes = new AtomicInteger(0); // Shared counter for total primes
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<Future<Integer>> futures = new ArrayList<>();
        int range = maxNumber / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = i * range + 1;
            int end = (i == numThreads - 1) ? maxNumber : (i + 1) * range;
            futures.add(executor.submit(new PrimeCounterTask(start, end, totalPrimes)));
        }

        Thread progressReporter = new Thread(() -> {
            while (!executor.isTerminated() && !cancel) {
                try {
                    Thread.sleep(3000);
                    textArea.append("Primes found so far = " + totalPrimes.get() + "\n");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        progressReporter.start();

        try {
            for (Future<Integer> future : futures) {
                if (cancel) break;
                future.get(); 
            }
        } catch (Exception e) {
            textArea.append("Error during calculation: " + e.getMessage() + "\n");
        } finally {
            executor.shutdownNow(); 
            try {
                progressReporter.join(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!cancel) {
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
            textArea.append("Calculation complete! Total primes: " + totalPrimes.get() + "\n");
            textArea.append("Time taken: " + timeTaken/1000 + " seconds\n");
        }

        SwingUtilities.invokeLater(() -> {
            startButton.setEnabled(true);
            cancelButton.setEnabled(false);
        });
    }

    private class PrimeCounterTask implements Callable<Integer> {
        private final int start;
        private final int end;
        private final AtomicInteger totalPrimes;

        public PrimeCounterTask(int start, int end, AtomicInteger totalPrimes) {
            this.start = start;
            this.end = end;
            this.totalPrimes = totalPrimes;
        }

        @Override
        public Integer call() {
            int primeCount = 0;
            for (int i = start; i <= end && !cancel; i++) {
                if (isPrime(i)) {
                    primeCount++;
                    totalPrimes.incrementAndGet();
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
        SwingUtilities.invokeLater(FindPrimesGUI::new);
    }
}

//
/*It took 18 seconds to find primes until 50000000, 
 *12 seconds using 2 background threads,
 *9 seconds using 4 threads,
 *6 seconds using 6 threads,
 *and 6 using 8 and 10 threads from where no more speed up was observed
 */
