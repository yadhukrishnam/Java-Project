package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import core.Authentication;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FormRegistration extends JFrame {
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtRegistrationId;
	public FormRegistration() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(400, 100, 430, 250);
		setVisible(true);
		setTitle("User Account Registration"); 
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 36, 178, 15);
		getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(171, 34, 222, 19);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblAccountType = new JLabel("Account Type");
		lblAccountType.setBounds(28, 68, 178, 15);
		getContentPane().add(lblAccountType);
		
		JComboBox<String> cmbAccountType = new JComboBox();
		cmbAccountType.setBounds(171, 63, 222, 20);
		getContentPane().add(cmbAccountType);
		cmbAccountType.addItem("admin");
		cmbAccountType.addItem("siteadmin");
		cmbAccountType.addItem("client");
		cmbAccountType.addItem("supplier");
		cmbAccountType.setSelectedIndex(-1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(28, 95, 178, 15);
		getContentPane().add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(171, 95, 223, 19);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblRegistrationId = new JLabel("Registration ID");
		lblRegistrationId.setBounds(28, 122, 146, 15);
		getContentPane().add(lblRegistrationId);
		
		txtRegistrationId = new JTextField();
		txtRegistrationId.setText("0");
		txtRegistrationId.setColumns(10);
		txtRegistrationId.setBounds(170, 122, 223, 19);
		getContentPane().add(txtRegistrationId);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authentication auth = new Authentication(txtUsername.getText(), txtPassword.getText());
				auth.AccountType = (String) cmbAccountType.getSelectedItem();
				if (auth.AccountType != "admin")
					auth.UserId = Integer.parseInt(txtRegistrationId.getText());
				else
					auth.UserId = 0;
				
				if (auth.CreateAccount() == true) 
				{
					JOptionPane.showMessageDialog(null, "User registration success !");
				} else {
					JOptionPane.showMessageDialog(null, "Could not register user !");
				}
			}
		});
		btnNewButton.setBounds(171, 149, 222, 25);
		getContentPane().add(btnNewButton);
		
		cmbAccountType.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (cmbAccountType.getSelectedItem() == "admin")
		    	{
		    		lblRegistrationId.setVisible(false);
		    		txtRegistrationId.setVisible(false);
		    	} else {
		    		lblRegistrationId.setVisible(true);
		    		txtRegistrationId.setVisible(true);
		    	}
		    }
		});
	}
}
