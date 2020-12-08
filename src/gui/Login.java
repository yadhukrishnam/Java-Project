package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;
import core.ClsAuthentication;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private Dashboard db ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Dashboard db = new Dashboard();
					
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
		
		frame = new JFrame("Authentication");
		frame.getContentPane().setFont(new Font("Cantarell", Font.PLAIN, 12));
		frame.getContentPane().setBackground(new Color(102, 153, 255));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    frame.setAlwaysOnTop(true);
	    db = new Dashboard();
	    db.setEnabled(false);
		textField = new JTextField();
		textField.setBounds(167, 89, 238, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
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
		
		JButton btnSubmit = new JButton("Login");
		btnSubmit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				   ClsAuthentication auth = new ClsAuthentication(textField.getText(), new String(passwordField.getPassword()));
				   auth.Authenticate();
				   if (auth.isAuthenticated)
				   {
					   switch (auth.AccountType) {
						   case "supplier":
							   		System.out.print("Supplier");
							   		break;
						   case "client" :
							   		System.out.print("Client");
							   		break;
						   case "admin":
							   		Dashboard db = new Dashboard(); 
							   		break;
						   case "siteadmin" :
							   		System.out.print("Site Admin");
							   		break;
					   }
				   } else {
					   JOptionPane.showMessageDialog(null, "Wrong Username or Password.");
				   }
		}
	});

		btnSubmit.setBounds(284, 181, 117, 34);
		frame.getContentPane().add(btnSubmit);
		JLabel lblAuthentication = new JLabel("LOGIN");
		lblAuthentication.setBounds(45, 31, 348, 15);
		frame.getContentPane().add(lblAuthentication);	
  	  	frame.setLocationRelativeTo(null);
  	  	frame.setResizable(false);
	}
}
