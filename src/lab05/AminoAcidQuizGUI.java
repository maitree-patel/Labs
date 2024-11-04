package lab05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;


public class AminoAcidQuizGUI {
    private JFrame frame;
    private JTextField questionField, answerField;
    private JButton startButton, submitButton, cancelButton;
    private JLabel timerLabel, scoreLabel;
    private Timer timer;
    private int timeLeft = 30;
    private int score = 0;
    
    char aa_code[] = {'A', 'R', 'N', 'D', 
	          		  'C', 'Q', 'E', 'G', 
	          		  'H', 'I', 'L', 'K', 
	          		  'M', 'F', 'P', 'S', 
	          		  'T', 'W', 'Y', 'V'};

    String aa_fullname[] = {"alanine", "arginine", "asparagine", "aspartic acid", 
	                		"cysteine", "glutamine", "glutamic acid", "glycine", 
	                		"histidine", "isoleucine", "leucine", "lysine", 
	                		"methionine", "phenylalanine", "proline", "serine", 
	                		"threonine", "tryptophan", "tyrosine", "valine"};
    
    String aa_ans;
    String aa_ques;
    int totalQues;

    public AminoAcidQuizGUI() {
        frame = new JFrame("Amino Acid Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Main panel for quiz content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        questionField = new JTextField();
        questionField.setEditable(false);
        mainPanel.add(questionField, BorderLayout.NORTH);

        timerLabel = new JLabel("Time left: 30 seconds", SwingConstants.CENTER);
        mainPanel.add(timerLabel, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.NORTH);

        // input answer panel
        JPanel answerPanel = new JPanel(new GridLayout(2, 1));

        JPanel inputPanel = new JPanel();
        answerField = new JTextField(10);
        inputPanel.add(new JLabel("Answer:"));
        inputPanel.add(answerField);
        answerPanel.add(inputPanel);

        // defining buttons
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start Quiz");
        startButton.addActionListener(new StartButtonListener());
        buttonPanel.add(startButton);

        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(new SubmitButtonListener());
        buttonPanel.add(submitButton);

        cancelButton = new JButton("Cancel Quiz");
        cancelButton.addActionListener(new CancelButtonListener());
        buttonPanel.add(cancelButton);

        answerPanel.add(buttonPanel);
        frame.add(answerPanel, BorderLayout.CENTER);

        // score display
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        frame.add(scoreLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void startQuiz() {
        timeLeft = 30;
        score = 0;
        scoreLabel.setText("Score: 0");
        timerLabel.setText("Time left: 30 seconds");
        generateRandomQuestion();
        startTimer();
    }

    private void generateRandomQuestion() {
        Random random = new Random();
        /*int index = random.nextInt(aminoAcids.length);
        currentQuestion = aminoAcids[index][0];
        currentAnswer = aminoAcids[index][1];
        questionField.setText("What is the single-letter code for " + currentQuestion + "?");
        */
        int index = random.nextInt(20);
		
		aa_ques = aa_fullname[index];
		aa_ans = ""+aa_code[index]; 
		totalQues++;
		questionField.setText("What is the single character code for " + aa_ques + " ?");
    }

    private void startTimer() {
       Timer timer = new Timer(1000, new ActionListener()
       {
    	    @Override
            public void actionPerformed(ActionEvent e) 
    	    {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText("Time left: " + timeLeft + " seconds");
                } else {
                    ((Timer) e.getSource()).stop();
                    timerLabel.setText("Time's up!");
            }
        }
       });
        timer.start();
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startQuiz();
            answerField.setEditable(true);
        }
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timeLeft > 0) {
                String answer = answerField.getText().trim().toUpperCase();
                if (answer.equals(aa_ans)) {
                    score++;
                    scoreLabel.setText("Score: " + score + "/" + totalQues);
                }
                answerField.setText("");
                generateRandomQuestion();
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timer != null && timer.isRunning()) {
                ((Timer) e.getSource()).stop();
            }
            questionField.setText("");
            timerLabel.setText("Quiz Over!");
            scoreLabel.setText("Your Total Score is: " + score + "/" + totalQues);
            answerField.setEditable(false);
        }
    }

    public static void main(String[] args) {
        new AminoAcidQuizGUI();
    }
}
