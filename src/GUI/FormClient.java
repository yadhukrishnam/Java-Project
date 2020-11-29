package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import core.Client;

public class FormClient extends JFrame {
	private JFrame frame;
	public JTextField txtFirstName;
	public JTextField txtMidName;
	private JLabel lblNewLabel_2;
	public JTextField txtLastname;
	private JLabel lblNewLabel_4;
	public JTextField txtMobile;
	private JLabel lblNewLabel_5;
	public JTextArea txtAddress; 
	public int ClientId = 0; 
	
	public void setData(Client cl)
	{
		this.ClientId = cl.ClientId; 
		this.txtFirstName.setText(cl.FirstName);
		this.txtMidName.setText(cl.MidName);
		this.txtLastname.setText(cl.LastName);
		this.txtAddress.setText(cl.Address);
		this.txtMobile.setText(String.valueOf(cl.MobileNo));
		if (this.ClientId != 0)
			this.setTitle("Update Client");
	}
	/**
	 * Create the frame.
	 */
	public FormClient() {
		this.setTitle("New Client");
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(36, 64, 164, 14);
		getContentPane().add(lblNewLabel);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(175, 62, 216, 20);
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Middle Name");
		lblNewLabel_1.setBounds(36, 89, 186, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtMidName = new JTextField();
		txtMidName.setBounds(175, 87, 216, 20);
		getContentPane().add(txtMidName);
		txtMidName.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(36, 120, 164, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(175, 115, 216, 20);
		getContentPane().add(txtLastname);
		txtLastname.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Mobile No");
		lblNewLabel_4.setBounds(36, 151, 143, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtMobile = new JTextField();
		txtMobile.setBounds(175, 144, 216, 20);
		getContentPane().add(txtMobile);
		txtMobile.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setBounds(36, 176, 143, 78);
		getContentPane().add(lblNewLabel_5);
		
		txtAddress = new JTextArea();
		txtAddress.setBounds(175, 176, 216, 78);
		getContentPane().add(txtAddress);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client cl = new Client(ClientId, txtFirstName.getText(), txtMidName.getText(), txtLastname.getText(), txtAddress.getText(), txtMobile.getText());
				if (ClientId == 0)
				{
					if (cl.register() ) 
					{
						JOptionPane.showMessageDialog(null, "New client registered !");
					} else {
						JOptionPane.showMessageDialog(null, "Could not register new client.");	
					}
					
				} else {
					if (cl.update() ) 
					{
						JOptionPane.showMessageDialog(null, "Client details updated !");
					} else {
						JOptionPane.showMessageDialog(null, "Could not update client.");	
					}
				}

				dispose();
			}
		});
		btnNewButton.setBounds(302, 268, 89, 23);
		getContentPane().add(btnNewButton);
		
	}
}
