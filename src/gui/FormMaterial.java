package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import core.Material;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMaterial extends JFrame {

	private JFrame frame;
	private JTextField textMaterialID;
	private JTextField textMaterialName;
	private JTextField textQtyAvailable;
	private JTextField textReOrderLevel;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the application.
	 */
	public void setData(Material ml)
	{
		this.textMaterialID.setText(String.valueOf(ml.MaterialID)); 
		this.textMaterialName.setText(ml.MaterialName);
		this.textQtyAvailable.setText(String.valueOf(ml.QtyAvailable)); 
		this.textReOrderLevel.setText(String.valueOf(ml.ReOrderLevel)); 

			this.setTitle("Update Client");
	}
	public FormMaterial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Material ID");
		lblNewLabel.setBounds(29, 48, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Material Name");
		lblNewLabel_1.setBounds(29, 84, 159, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity Available");
		lblNewLabel_2.setBounds(29, 125, 159, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ReOrderLevel");
		lblNewLabel_3.setBounds(29, 164, 159, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(":");
		lblNewLabel_4.setBounds(190, 48, 30, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(":");
		lblNewLabel_5.setBounds(190, 78, 30, 20);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(":");
		lblNewLabel_6.setBounds(190, 125, 30, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(":");
		lblNewLabel_7.setBounds(190, 164, 30, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		textMaterialID = new JTextField();
		textMaterialID.setBounds(238, 46, 151, 20);
		frame.getContentPane().add(textMaterialID);
		textMaterialID.setColumns(10);
		
		textMaterialName = new JTextField();
		textMaterialName.setBounds(238, 82, 151, 20);
		frame.getContentPane().add(textMaterialName);
		textMaterialName.setColumns(10);
		
		textQtyAvailable = new JTextField();
		textQtyAvailable.setBounds(238, 123, 151, 20);
		frame.getContentPane().add(textQtyAvailable);
		textQtyAvailable.setColumns(10);
		
		textReOrderLevel = new JTextField();
		textReOrderLevel.setBounds(238, 162, 151, 20);
		frame.getContentPane().add(textReOrderLevel);
		textReOrderLevel.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Material M1=new Material(Integer.parseInt(textMaterialID.getText()),textMaterialName.getText(),Integer.parseInt(textQtyAvailable.getText()),Integer.parseInt(textReOrderLevel.getText()));
				if(M1.register())
				{
					JOptionPane.showMessageDialog(null, "Materials updated !");
				}
				else {
					JOptionPane.showMessageDialog(null, "Materials not updated !");
				}
			}
		});
		btnSave.setBounds(300, 215, 89, 25);
		frame.getContentPane().add(btnSave);
		frame.setVisible(true);
	}

}
