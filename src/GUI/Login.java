package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */ 
	private void initialize() {
		 
		frame = new JFrame("Login to continue");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(167, 89, 238, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(45, 92, 106, 21);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 137, 106, 21);
		frame.getContentPane().add(lblPassword);
		 
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 134, 238, 27);
		frame.getContentPane().add(passwordField);
		
		JLabel msgLbl = new JLabel("");
		msgLbl.setBounds(57, 170, 215, 27);
		frame.getContentPane().add(msgLbl);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new Dashboard();
				frame.dispose();
				/*
				char[] password = passwordField.getPassword();
				char[] correctPass = new char[] {'1', '2', '3'};
				
				
				if (textField.getText().equals("admin") && Arrays.equals(password, correctPass))
				{
					msgLbl.setText("Loading.");
					Dashboard db = new Dashboard();
					frame.dispose();
					
				} else {
					JOptionPane.showMessageDialog(null, "Ayn nee edhaada naaye?", "Wrong credentials", 1);
					//JOptionPane.showMessageDialog(null, "Wrong username or password");
				}*/
				
				
			}
		});

		btnSubmit.setBounds(288, 173, 117, 34);
		frame.getContentPane().add(btnSubmit);
		

		
		JLabel lblAuthentication = new JLabel("LOGIN");
		lblAuthentication.setBounds(45, 31, 348, 15);
		frame.getContentPane().add(lblAuthentication);	
	}
}
