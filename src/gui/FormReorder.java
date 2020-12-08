package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import core.Material;
import core.Order;
import core.Supplier;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class FormReorder  extends JFrame {
	private int MaterialID = 0; 
	private int SupplierID = 0;
	
	private JTextField textField;
	private JFrame frame;
	private JLabel lblTxtmaterialid;
	private JLabel lblTxtmaterialname;
	private JLabel lblTxtsupplierid;
	private JLabel lblTxtsuppliername;
	private JLabel lblTxtqtyavailable;
	private JLabel lblTxtreorderlevel;
	
	public FormReorder(Material mt) {
		initialize();
		this.MaterialID = mt.MaterialID; 
		this.SupplierID = mt.SupplierId;
		
		lblTxtmaterialid.setText(String.valueOf(mt.MaterialID));
		lblTxtmaterialname.setText(mt.MaterialName);
		lblTxtsupplierid.setText(String.valueOf(mt.SupplierId));
		lblTxtsuppliername.setText(mt.SupplierName);
		lblTxtqtyavailable.setText(String.valueOf(mt.QtyAvailable));
		lblTxtreorderlevel.setText(String.valueOf(mt.ReOrderLevel));
		
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.getContentPane().setLayout(new GridLayout(8, 2));
		
		JLabel lblMaterialId = new JLabel("Material ID");
		frame.getContentPane().add(lblMaterialId);
		
		lblTxtmaterialid = new JLabel("txtMaterialId");
		frame.getContentPane().add(lblTxtmaterialid);
		
		JLabel lblMaterialName = new JLabel("Material Name");
		frame.getContentPane().add(lblMaterialName);
		
		lblTxtmaterialname = new JLabel("txtMaterialName");
		frame.getContentPane().add(lblTxtmaterialname);
		
		JLabel lblSupplierId = new JLabel("Supplier ID");
		frame.getContentPane().add(lblSupplierId);
		
		lblTxtsupplierid = new JLabel("txtSupplierId");
		frame.getContentPane().add(lblTxtsupplierid);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		frame.getContentPane().add(lblSupplierName);
		
		lblTxtsuppliername = new JLabel("txtSupplierName");
		frame.getContentPane().add(lblTxtsuppliername);
		
		JLabel lblAvailableQuantity = new JLabel("Available Quantity");
		frame.getContentPane().add(lblAvailableQuantity);
		
		lblTxtqtyavailable = new JLabel("txtQtyAvailable");
		frame.getContentPane().add(lblTxtqtyavailable);
		
		JLabel lblReorderLevel = new JLabel("Reorder Level");
		frame.getContentPane().add(lblReorderLevel);
		
		lblTxtreorderlevel = new JLabel("txtReorderLevel");
		frame.getContentPane().add(lblTxtreorderlevel);
		
		JLabel lblRequiredQuantity = new JLabel("Required Quantity");
		frame.getContentPane().add(lblRequiredQuantity);
		
		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		frame.getContentPane().add(btnViewOrders);
		
		JButton btnNewButton = new JButton("Place Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Order OrderObj = new Order("IN", MaterialID, Integer.parseInt(textField.getText()), SupplierID);
				if (OrderObj.PlaceOrder()) {
					JOptionPane.showMessageDialog(null, "Could not place order.");
				} else {
					JOptionPane.showMessageDialog(null, "Order placed successfully !");
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		System.out.print("REACHED");
	}
	}
