package lab06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FindPrimesGUI {

    // Declare GUI components
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private JButton slowButton;
    private JButton cancelButton;

    // Volatile boolean to track cancellation
    private volatile boolean cancel = false;

    public FindPrimesGUI() {
        // Initialize the GUI components
        frame = new JFrame("Prime Number Counter");
        textArea = new JTextArea(15, 40);
        inputField = new JTextField(10); // Input field for user to enter a large number
        slowButton = new JButton("Start Counting");
        cancelButton = new JButton("Cancel");

        // Set up text area properties
        textArea.setEditable(false); // Make the text area read-only
        JScrollPane scrollPane = new JScrollPane(textArea); // Add scrolling

        // Set up input panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter a number:"));
        inputPanel.add(inputField);
        inputPanel.add(slowButton);
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
        slowButton.addActionListener(e -> {
            // Get user input and validate it
            String inputText = inputField.getText();
            try {
                int maxNumber = Integer.parseInt(inputText);
                if (maxNumber < 2) {
                    textArea.append("Please enter a number greater than or equal to 2.\n");
                    return;
                }

                // Reset cancel flag and UI states
                cancel = false;
                slowButton.setEnabled(false);
                cancelButton.setEnabled(true);
                textArea.append("Starting prime number calculation up to " + maxNumber + "...\n");

                // Start the prime counting thread
                Thread primeThread = new Thread(new PrimeCounterRunnable(maxNumber));
                primeThread.start();
            } catch (NumberFormatException ex) {
                textArea.append("Invalid input. Please enter a valid whole number.\n");
            }
        });

        // Cancel button listener
        cancelButton.addActionListener(e -> {
            cancel = true; // Signal the thread to stop
            textArea.append("Calculation canceled by user.\n");
        });
    }

    private class PrimeCounterRunnable implements Runnable {
        private final int maxNumber;

        public PrimeCounterRunnable(int maxNumber) {
            this.maxNumber = maxNumber;
        }

        @Override
        public void run() {
            try {
                int primeCount = 0;

                for (int currentNumber = 2; currentNumber <= maxNumber && !cancel; currentNumber++) {
                    if (isPrime(currentNumber)) {
                        primeCount++;
                        textArea.append("Prime #" + primeCount + ": " + currentNumber + "\n");

                        // Add delay for observation (optional)
                        Thread.sleep(50);
                    }
                }

                if (!cancel) {
                    textArea.append("Calculation complete! Total primes: " + primeCount + "\n");
                }
            } catch (Exception ex) {
                textArea.append("Error: " + ex.getMessage() + "\n");
                ex.printStackTrace();
            }

            // Re-enable buttons on completion or cancellation
            SwingUtilities.invokeLater(() -> {
                slowButton.setEnabled(true);
                cancelButton.setEnabled(false);
            });
        }

        // Helper method to check if a number is prime
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
