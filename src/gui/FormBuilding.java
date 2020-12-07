package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.Building;
import core.Site;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;

public class FormBuilding extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField txtBuildingName;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField txtBuildingId;
	private JTextField txtYear;
	private JButton btnSave;
	private JTextField txtCost;
	private JComboBox<String[]> cmbBuildingType;
	private JLabel lblNewLabel_3_2;
	private JLabel lblNewLabel_7_2;
	private JComboBox<String> cmbLocation;
	private JLabel lblNewLabel_3_3;
	private JComboBox<String> cmbSiteName;
	private JLabel lblNewLabel_7_3;
	private boolean isUpdate = false;
	private JTextField txtOwnerId;
	
	public void setData(Building B)
	{
		txtBuildingName.setText(B.BuildingName);
		txtBuildingId.setText(String.valueOf(B.BuildingId));
		txtYear.setText(String.valueOf(B.ConstructionYear));
		txtCost.setText(String.valueOf(B.Cost));
		cmbLocation.setSelectedItem(B.SiteLocation);
		cmbBuildingType.setSelectedItem(B.BuildingType);
		cmbSiteName.setSelectedItem(B.SiteName);
		txtOwnerId.setText(String.valueOf(B.ClientId)); 
		isUpdate = true;
	}
	
	public FormBuilding() {
		this.setTitle("New Building");
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Building Name");
		lblNewLabel.setBounds(47, 80, 150, 14);
		getContentPane().add(lblNewLabel);
		
		txtBuildingName = new JTextField();
		txtBuildingName.setBounds(256, 78, 165, 20);
		getContentPane().add(txtBuildingName);
		txtBuildingName.setColumns(10);
		
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
		
		txtBuildingId = new JTextField();
		txtBuildingId.setBounds(256, 104, 165, 20);
		getContentPane().add(txtBuildingId);
		txtBuildingId.setColumns(10);
		
		txtYear = new JTextField();
		txtYear.setBounds(256, 160, 165, 20);
		getContentPane().add(txtYear);
		txtYear.setColumns(10);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Building Bl = new Building(
						Integer.parseInt(txtBuildingId.getText()),
						txtBuildingName.getText(), 
						String.valueOf(cmbBuildingType.getSelectedItem()),
						Integer.parseInt(txtYear.getText()),
						String.valueOf(cmbSiteName.getSelectedItem()),
						Long.parseLong(txtCost.getText()),
						Integer.parseInt(txtOwnerId.getText()) 
				);
				if (isUpdate == true) 
				{
					if (Bl.updateDetails())
					{
						JOptionPane.showMessageDialog(null, "Building updated !");
					} else {
						JOptionPane.showMessageDialog(null, "Could not update building.");
					}
				} else {
					if (Bl.register())
					{
						JOptionPane.showMessageDialog(null, "New Building added !");
					} else {
						JOptionPane.showMessageDialog(null, "Could not add building.");
					}
				}
				dispose(); 
			}
		});
		btnSave.setBounds(305, 313, 116, 25);
		getContentPane().add(btnSave);
		
		JLabel lblNewLabel_3_1 = new JLabel("Cost");
		lblNewLabel_3_1.setBounds(47, 188, 151, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_7_1 = new JLabel(":");
		lblNewLabel_7_1.setBounds(223, 187, 15, 14);
		contentPane.add(lblNewLabel_7_1);
		
		txtCost = new JTextField();
		txtCost.setColumns(10);
		txtCost.setBounds(256, 186, 165, 20);
		contentPane.add(txtCost);
		
		cmbBuildingType = new JComboBox();
		cmbBuildingType.setModel(new DefaultComboBoxModel(new String[] {"Apartment", "Office"}));
		
		cmbBuildingType.setBounds(256, 131, 165, 24);
		contentPane.add(cmbBuildingType);
		
		lblNewLabel_3_2 = new JLabel("Location");
		lblNewLabel_3_2.setBounds(47, 214, 151, 14);
		contentPane.add(lblNewLabel_3_2);
		
		lblNewLabel_7_2 = new JLabel(":");
		lblNewLabel_7_2.setBounds(223, 213, 15, 14);
		contentPane.add(lblNewLabel_7_2);
		
		cmbLocation = new JComboBox();
		cmbLocation.setSelectedIndex(-1);
		cmbLocation.setBounds(256, 209, 165, 24);
		contentPane.add(cmbLocation);
		Site s = new Site(); 
		ArrayList<String> location = s.getLocations();
		if (location.size() == 0)
		{
			JOptionPane.showMessageDialog(null, "No sites exist.");
			dispose(); 
		}
		cmbLocation.setSelectedIndex(-1);
		cmbLocation.setModel(new DefaultComboBoxModel<String>(location.toArray(new String[0])));
		cmbLocation.setSelectedIndex(-1);
		cmbLocation.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	cmbSiteName.removeAllItems();
		    	ArrayList<String> sites = s.getSites((String) cmbLocation.getSelectedItem());
		    	for(String el: sites)
		    	{
		    		cmbSiteName.addItem(el);
		    	}
		    }
		});
		
		lblNewLabel_3_3 = new JLabel("Site Name");
		lblNewLabel_3_3.setBounds(46, 282, 151, 14);
		contentPane.add(lblNewLabel_3_3);
		
		cmbSiteName = new JComboBox();
		cmbSiteName.setBounds(256, 277, 165, 24);
		contentPane.add(cmbSiteName);
		
		lblNewLabel_7_3 = new JLabel(":");
		lblNewLabel_7_3.setBounds(223, 284, 15, 14);
		contentPane.add(lblNewLabel_7_3);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("Owner ID");
		lblNewLabel_3_3_1.setBounds(46, 247, 151, 14);
		contentPane.add(lblNewLabel_3_3_1);
		
		JLabel lblNewLabel_7_3_1 = new JLabel(":");
		lblNewLabel_7_3_1.setBounds(223, 247, 15, 14);
		contentPane.add(lblNewLabel_7_3_1);
		
		txtOwnerId = new JTextField();
		txtOwnerId.setColumns(10);
		txtOwnerId.setBounds(256, 245, 165, 20);
		contentPane.add(txtOwnerId);
	}
}
