package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import core.Supplier;


public class SupplierList extends JFrame {

	private DefaultTableModel tableModel; 
	private JPanel contentPane;
	private JTable table;
	private Supplier S1;
	
	public void loadTable()
	{
		tableModel.setRowCount(0);
		S1 = new Supplier();
		ArrayList<Supplier> suppliers= S1.getSuppliers();
		
		for(Supplier SupplierObj :suppliers)
		{
			Object[] objs = {SupplierObj.SupplierId, SupplierObj.SupplierName, SupplierObj.Address, SupplierObj.MaterialId, SupplierObj.SupplierContact, SupplierObj.SupplierMailId};
			tableModel.addRow(objs);
			
		}
		
	}
	

	public void loadTable(ArrayList<Supplier> suppliers)
	{
		tableModel.setRowCount(0);
		S1 = new Supplier();
		for(Supplier SupplierObj :suppliers)
		{
			Object[] objs = {SupplierObj.SupplierId, SupplierObj.SupplierName, SupplierObj.Address, SupplierObj.MaterialId, SupplierObj.SupplierContact, SupplierObj.SupplierMailId};
			tableModel.addRow(objs);
		}
	}
	
	public SupplierList() {
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String col[] = {"SupplierID","SupplierName", "Address", "MaterialId", "SupplierContact", "SupplierMailId"};
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
	            	
	            	// to detect double click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow();
	               // select a row
	               FormSupplier S1=new FormSupplier(); 
	               Supplier currentSupplier = new Supplier();
                   S1.setData(currentSupplier.getSupplier(Integer.parseInt(table.getValueAt(row, 0).toString())));

                   dispose();

	            }
	         }
	     });
	}

}
