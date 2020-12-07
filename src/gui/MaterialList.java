package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import core.Material;
import core.Site;
import core.Client;

public class MaterialList extends JFrame {
	private DefaultTableModel tableModel; 
	private JPanel contentPane;
	private JTable table;
	private Material ml;
	public boolean isReorder = false;
	
	public void loadTable(ArrayList<Material> materials)
	{
		tableModel.setRowCount(0);
		for(Material MaterialObj :materials)
		{
			
			Object[] objs = {MaterialObj.MaterialID, MaterialObj.MaterialName, MaterialObj.QtyAvailable, MaterialObj.ReOrderLevel};
			tableModel.addRow(objs);
			
		}
	}
	
	public void loadTable()
	{
		tableModel.setRowCount(0);
		 ml = new Material();
		ArrayList<Material> materials = ml.getMaterials();
		
		for(Material MaterialObj :materials)
		{
			Object[] objs = {MaterialObj.MaterialID, MaterialObj.MaterialName, MaterialObj.QtyAvailable, MaterialObj.ReOrderLevel};
			tableModel.addRow(objs);
		}
	}
	public void generateForm()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
	}
		
		
	
	public MaterialList() {
		generateForm();
		String col[] = {"Material ID","Material Name","Available Quantity", "Re-order Level"};
		tableModel = new DefaultTableModel(col, 0);
		
		table = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		loadTable();
		contentPane.add(new JScrollPane(table));
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {   
	            	
	            	JTable target = (JTable)me.getSource();
	            	int row = target.getSelectedRow(); // select a row
	            	
	            	if (isReorder == false) {
	            		FormMaterial fm=new FormMaterial();
	            		Material currentMaterial = new Material(Integer.parseInt(table.getValueAt(row, 0).toString()));
		           		fm.setData(currentMaterial);
		           		fm.isUpdate = true; 
	            	} else {
	            		Material mt = new Material(Integer.parseInt(table.getValueAt(row, 0).toString()));
	            		FormReorder fr = new FormReorder(mt);
	            	}
	               		              
	           		dispose(); // to detect double click events
	              
	            }
	         }
	     });
	}

}
