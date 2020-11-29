package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.Building;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;

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
	private JTextField txtBuildingType;
	private JTextField txtYear;
	private JButton btnSave;
	private JTextField txtCost;

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
		
		txtBuildingType = new JTextField();
		txtBuildingType.setBounds(256, 134, 165, 20);
		getContentPane().add(txtBuildingType);
		txtBuildingType.setColumns(10);
		
		txtYear = new JTextField();
		txtYear.setBounds(256, 159, 165, 20);
		getContentPane().add(txtYear);
		txtYear.setColumns(10);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Building Bl = new Building(
						Integer.parseInt(txtBuildingId.getText()),
						txtBuildingName.getText(), txtBuildingType.getText(),
						Integer.parseInt(txtYear.getText()),
						1,
						Long.parseLong(txtCost.getText()),
						1
				);
				if (Bl.register())
				{
					JOptionPane.showMessageDialog(null, "New Building added !");
				} else {
					JOptionPane.showMessageDialog(null, "Could not add building.");
				}
			}
		});
		btnSave.setBounds(305, 230, 116, 25);
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
	}
}
