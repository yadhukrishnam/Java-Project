package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import core.Material;
import core.Order;
import core.Site;

import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SupplierOrderList extends JFrame {
	private JTable pendingOrderTable;
	private JTable pastOrders;
	private DefaultTableModel tableModel; 
	public String mode;
	
	public void setMode(String mode)
	{
		this.mode = mode; 
	}
	
	public void SupplierPastOrders(ArrayList<Order> Orders) {
		for(Order OrderObj : Orders)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(OrderObj.OrderDate);  
			
			Material m = new Material(OrderObj.MaterialId); 
			Object[] objs = {OrderObj.OrderId,OrderObj.Mode, OrderObj.MaterialId, m.MaterialName , OrderObj.toId, m.SupplierName, OrderObj.QuantityOrdered,  strDate};
			
			tableModel.addRow(objs);
		}
	}

	public void SupplierPendingOrders()
	{
		tableModel.setRowCount(0);
		Order order = new Order(); 
		ArrayList<Order> Orders = order.getPendingOrders();
		
		for(Order OrderObj : Orders)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(OrderObj.OrderDate);  
			
			Material m = new Material(OrderObj.MaterialId); 
			Object[] objs = {OrderObj.OrderId,OrderObj.Mode, OrderObj.MaterialId, m.MaterialName , OrderObj.toId, m.SupplierName, OrderObj.QuantityOrdered,  strDate};
			
			tableModel.addRow(objs);
		}
		
	}
	
	public SupplierOrderList(int SupplierId, String mode) {
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		setLocationRelativeTo(null);
		if (mode == "PENDING")
		{
			String col[] = {"Order ID","Mode", "Material ID" , "Material Name", "Quantity Ordered" , "Order Date"};
			tableModel = new DefaultTableModel(col, 0);
			SupplierPendingOrders(); 
		} else if (mode == "FULFILLED"){
			String col[] = {"Order ID","Mode", "Material ID" , "Material Name", "Quantity Ordered" , "Order Date", "Fulfilled Date"};
			tableModel = new DefaultTableModel(col, 0);
			Order o = new Order();
			SupplierPastOrders(o.getPastOrders());
		}
		
		pendingOrderTable = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		
		
		getContentPane().add(new JScrollPane(pendingOrderTable));

	}

}
