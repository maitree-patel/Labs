package lab05;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AminoAcidQuizGUI extends JFrame
{
	private JButton doubleButton = new JButton("Start Quiz!");
	private JTextField aTextField = new JTextField();
	
	public AminoAcidQuizGUI() 
	{
		super("Amino Acid Quiz");
		
		//setting size of window
		setSize(200,200);
		
		//location of window in the center
		setLocationRelativeTo(null);
		
		//adding button and text
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(doubleButton, BorderLayout.SOUTH);
		getContentPane().add(aTextField, BorderLayout.CENTER);
		
		//displays text on GUI window
		aTextField.setText("GUI test!");
		
		//exit java on closing window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//making window visible
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new AminoAcidQuizGUI();
	}

}
