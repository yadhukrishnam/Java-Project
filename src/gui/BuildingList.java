package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import core.Building;
import core.Client;

public class BuildingList extends JFrame {
	private DefaultTableModel tableModel; 
	private JPanel contentPane;
	private JTable table;

	public void populateTable()
	{
		tableModel.setRowCount(0);
		Building bl = new Building();
		ArrayList<Building> buildings = bl.getBuildings();
		
		for(Building BO :buildings)
		{
			Object[] objs = {BO.BuildingId, BO.BuildingName, BO.BuildingType, BO.ConstructionYear, BO.SiteName, BO.Cost, BO.OwnerName};
			tableModel.addRow(objs);
		}
	}
	
	public BuildingList() {
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		String col[] = {"Building ID","Building Name", "Building Type", "Year Of Construction", "Location", "Cost", "Current Owner"};
		tableModel = new DefaultTableModel(col, 0);
		
		table = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		populateTable(); 
		
		contentPane.add(new JScrollPane(table));
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect double click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               
	               
	               FormBuilding fb = new FormBuilding();
		           Building b = new Building();
		           fb.setData(b.getBuilding(Integer.parseInt(table.getValueAt(row, 0).toString())));   
	               
	               dispose();
	            }
	         }
	     });
	}

}
