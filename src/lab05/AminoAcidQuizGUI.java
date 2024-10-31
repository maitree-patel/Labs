//
package lab05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AminoAcidQuizGUI extends JFrame implements ActionListener {
	
	@Override
    public void actionPerformed(ActionEvent e) {
        // check the answer when submit button is clicked
        checkAnswer();
    }

    private JTextField answerField; //an answer field
    private JLabel questionLabel, scoreLabel, timerLabel;
    private JButton submitButton;
    private Timer quizTimer;

    // initializing quiz data
    private int totalDuration = 30;
    private int timeRemaining;
    private int totalQuestions = 0;
    private int correctAnswers = 0;
    private Random random = new Random();

    private char[] aaCodes = {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
    private String[] aaNames = {"alanine", "arginine", "asparagine", "aspartic acid", "cysteine", "glutamine", "glutamic acid", 
                                "glycine", "histidine", "isoleucine", "leucine", "lysine", "methionine", "phenylalanine", 
                                "proline", "serine", "threonine", "tryptophan", "tyrosine", "valine"};
    private int currentIndex;

    public AminoAcidQuizGUI() {
        // Set up the main window
        setTitle("Amino Acid Quiz");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // quiz panel for questions, answers, and score
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridLayout(4, 1));
        
        questionLabel = new JLabel("Press 'Start Quiz' to begin!");
        quizPanel.add(questionLabel);
        
        answerField = new JTextField(5);
        quizPanel.add(answerField);
        
        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(this);
        quizPanel.add(submitButton);
        
        scoreLabel = new JLabel("Score: 0/0");
        quizPanel.add(scoreLabel);

        // timer display
        timerLabel = new JLabel("Time Remaining: " + totalDuration + " seconds");
        add(quizPanel, BorderLayout.CENTER);
        add(timerLabel, BorderLayout.SOUTH);

        // timer setup
        quizTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time Remaining: " + timeRemaining + " seconds");
                if (timeRemaining <= 0) {
                    quizTimer.stop();
                    endQuiz();
                }
            }
        });

        answerField.setEnabled(false);
        submitButton.setEnabled(false);
    }

    //method to start quiz 
    private void startQuiz() {
        timeRemaining = totalDuration;
        totalQuestions = 0;
        correctAnswers = 0;
        quizTimer.start();

        answerField.setEnabled(true);
        submitButton.setEnabled(true);
        questionLabel.setText("What is the single character code for...");
        askQuestion(); // asking a question when quiz starts
    }

    private void askQuestion() {
        currentIndex = random.nextInt(aaNames.length);
        questionLabel.setText("What is the single character code for " + aaNames[currentIndex] + "?");
        answerField.setText("");
    }

    private void checkAnswer() {
        String answer = answerField.getText().toUpperCase();
        totalQuestions++;

        if (answer.equals(String.valueOf(aaCodes[currentIndex]))) {
            correctAnswers++;
            JOptionPane.showMessageDialog(this, "Correct! Good job!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is " + aaCodes[currentIndex] + ".");
        }

        scoreLabel.setText("Score: " + correctAnswers + "/" + totalQuestions);
        askQuestion();
    }

    private void endQuiz() {
        answerField.setEnabled(false);
        submitButton.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Time's up! Your final score is " + correctAnswers + "/" + totalQuestions);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AminoAcidQuizGUI quizApp = new AminoAcidQuizGUI();
            quizApp.setVisible(true);
            quizApp.startQuiz();
        });
    }
}
