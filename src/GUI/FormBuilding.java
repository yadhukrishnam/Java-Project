package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FormBuilding extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;

	public FormBuilding() {
		this.setTitle("New Building");
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Building Name");
		lblNewLabel.setBounds(47, 80, 150, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(256, 78, 165, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Building ID");
		lblNewLabel_1.setBounds(47, 106, 165, 14);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Building Type");
		lblNewLabel_2.setBounds(47, 136, 150, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Year of Construction");
		lblNewLabel_3.setBounds(47, 162, 151, 14);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel(":");
		lblNewLabel_4.setBounds(223, 80, 15, 14);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel(":");
		lblNewLabel_5.setBounds(223, 136, 15, 14);
		getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel(":");
		lblNewLabel_6.setBounds(223, 105, 15, 14);
		getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel(":");
		lblNewLabel_7.setBounds(223, 161, 15, 14);
		getContentPane().add(lblNewLabel_7);
		
		textField_1 = new JTextField();
		textField_1.setBounds(256, 104, 165, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(256, 134, 165, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(256, 159, 165, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(305, 201, 116, 25);
		getContentPane().add(btnNewButton);
	}

}
