package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import core.Supplier;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormSupplier {

	private JFrame frame;
	private JTextField SupplierId;
	private JTextField Suppliername;
	private JTextField Address;
	private JTextField MaterialId;
	private JLabel lblNewLabel;
	private JLabel i;
	private JLabel p;
	private JTextField SupplierContact;
	private JTextField SupplierMailId;
	private JButton btnNewButton;
    private boolean isUpdate=false;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public FormSupplier() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void setData(Supplier S1)
	{
		this.SupplierId.setText(String.valueOf(S1.SupplierId)); 
		this.Suppliername.setText(S1.SupplierName);
		this.Address.setText(S1.Address);
		this.MaterialId.setText(String.valueOf(S1.MaterialId));
		this.SupplierContact.setText(String.valueOf(S1.SupplierContact));
		this.SupplierMailId.setText(String.valueOf(S1.SupplierMailId));
		this.isUpdate=true;
			
	}
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel s = new JLabel("SUPPLIER ID");
		s.setBounds(70, 49, 156, 13);
		frame.getContentPane().add(s);
		
		JLabel f = new JLabel("SUPPLIER NAME");
		f.setBounds(70, 82, 156, 13);
		frame.getContentPane().add(f);
		
		JLabel j = new JLabel("ADDRESS");
		j.setBounds(70, 111, 62, 22);
		frame.getContentPane().add(j);
		
		JLabel o = new JLabel("MATERIAL ID");
		o.setBounds(70, 143, 156, 13);
		frame.getContentPane().add(o);
		
		SupplierId = new JTextField();
		SupplierId.setBounds(236, 46, 190, 19);
		frame.getContentPane().add(SupplierId);
		SupplierId.setColumns(10);
		
		Suppliername = new JTextField();
		Suppliername.setBounds(236, 79, 190, 19);
		frame.getContentPane().add(Suppliername);
		Suppliername.setColumns(10);
		
		Address = new JTextField();
		Address.setBounds(236, 111, 190, 19);
		frame.getContentPane().add(Address);
		Address.setColumns(10);
		
		MaterialId = new JTextField();
		MaterialId.setBounds(236, 140, 190, 19);
		frame.getContentPane().add(MaterialId);
		MaterialId.setColumns(10);
		
		
		
		lblNewLabel = new JLabel("SUPPLIER DETAILS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(140, 10, 143, 13);
		frame.getContentPane().add(lblNewLabel);
		
		i = new JLabel("SUPPLIER CONTACT");
		i.setBounds(70, 178, 156, 13);
		frame.getContentPane().add(i);
		
		p = new JLabel("SUPPLIER MAIL ID");
		p.setBounds(70, 209, 156, 13);
		frame.getContentPane().add(p);
		
		SupplierContact = new JTextField();
		SupplierContact.setBounds(236, 175, 190, 19);
		frame.getContentPane().add(SupplierContact);
		SupplierContact.setColumns(10);
		
		SupplierMailId = new JTextField();
		SupplierMailId.setBounds(236, 206, 190, 19);
		frame.getContentPane().add(SupplierMailId);
		SupplierMailId.setColumns(10);
		
		btnNewButton = new JButton("ENTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Supplier S1 = new Supplier(
						Integer.parseInt(SupplierId.getText()),
						Suppliername.getText(),
						Address.getText(),
						Integer.parseInt(MaterialId.getText()),
						Long.parseLong(SupplierContact.getText()),
						SupplierMailId.getText()
						
						
				);
			if (isUpdate == false)
				{
				if (S1.register())
				{
					JOptionPane.showMessageDialog(null, "New Supplier added !");
				} else {
					JOptionPane.showMessageDialog(null, "Could not add  new Supplier.");
				}
			      }
			 else {
				if (S1.update() ) 
				{
					JOptionPane.showMessageDialog(null, "Client details updated !");
				} else {
					JOptionPane.showMessageDialog(null, "Could not update client.");	
				}
			}
			frame.dispose();
		    }
			
		});
		btnNewButton.setBounds(159, 232, 85, 21);
		frame.getContentPane().add(btnNewButton);
	}
}
