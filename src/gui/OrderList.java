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

public class OrderList extends JFrame {
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
			Object[] objs = {OrderObj.OrderId, OrderObj.MaterialId, m.MaterialName , OrderObj.toId, m.SupplierName, OrderObj.QuantityOrdered,  strDate};
			
			tableModel.addRow(objs);
		}
	}
	
	public void SitePendingRequests()
	{
		tableModel.setRowCount(0);
		ArrayList<Order> orders = new Order().getSitePendingOrders(); 
		for(Order order: orders)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(order.OrderDate);  
			
			Material m = new Material(order.MaterialId);
			Site s = new Site(order.toId);
			
			String col[] = {"Order ID", "Material ID" , "Material Name", "Site ID" , "Site Name", "Quantity Ordered" , "Order Date"};
			Object[] objs = {order.OrderId, order.MaterialId, m.MaterialName, order.toId,  s.SiteName , order.QuantityOrdered, strDate};
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
			Object[] objs = {OrderObj.OrderId, OrderObj.MaterialId, m.MaterialName , OrderObj.toId, m.SupplierName, OrderObj.QuantityOrdered,  strDate};
			
			tableModel.addRow(objs);
		}
		
	}
	
	public OrderList(String mode) {
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		setLocationRelativeTo(null);
		if (mode == "PENDING")
		{
			String col[] = {"Order ID", "Material ID" , "Material Name", "Supplier ID" , "Supplier Name", "Quantity Ordered" , "Order Date"};
			tableModel = new DefaultTableModel(col, 0);
			SupplierPendingOrders(); 
		} else if (mode == "FULFILLED"){
			String col[] = {"Order ID", "Material ID" , "Material Name", "Supplier ID" , "Supplier Name", "Quantity Ordered" , "Order Date"};
			tableModel = new DefaultTableModel(col, 0);
			Order o = new Order();
			SupplierPastOrders(o.getPastOrders());
		} else if (mode == "SITEREQ") {
			String col[] = {"Order ID", "Material ID" , "Material Name", "Site ID" , "Site Name", "Quantity Ordered" , "Order Date"};
			tableModel = new DefaultTableModel(col, 0);
			SitePendingRequests();
		}

		
		pendingOrderTable = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		
		
			pendingOrderTable.addMouseListener(new MouseAdapter() {
		         public void mouseClicked(MouseEvent me) {
		            if (me.getClickCount() == 2) {
		            	if (mode == "PENDING" || mode == "SITEREQ")  {
		            		
		            		Object[] options;
		            		if (mode == "PENDING") {
		            			 options = new Object[] { "Delete Order",  "Mark as Recieved"};
		            		} else {
		            			options = new Object[]  { "Delete Order",  "Mark as Fulfilled"};
		            		}
		
			            	JTable target = (JTable)me.getSource();
			            	int row = target.getSelectedRow(); 
			            	JPanel panel = new JPanel();
			            	panel.add(new JLabel("Select an action that you wish to perform: "));
			               
			            	int result = JOptionPane.showOptionDialog(null, panel, "Select option",
			                       JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
			                       null, options, null);
			               
			            	Order order = new Order(Integer.parseInt(pendingOrderTable.getValueAt(row, 0).toString()));
			            	if (result == JOptionPane.YES_OPTION){
			            	   if (order.deleteOrder() ) {
			            		   JOptionPane.showMessageDialog(null, "Order deleted.");
			            	   } else {
			            		   JOptionPane.showMessageDialog(null, "Could not delete order.");
			            	   }
			            	   SupplierPendingOrders();
			            	   
			               } else if (result == JOptionPane.NO_OPTION) {
			            	   if (mode == "SITEREQ") {
			            		   order.QuantityOrdered = -1 * order.QuantityOrdered; 
			            	   }
			            	   if (order.TransactOrder())
			            	   {
			            		   JOptionPane.showMessageDialog(null, "Transaction success.");
			            	   } else {
			            		   JOptionPane.showMessageDialog(null, "Could not delete order.");
			            	   }
			            	   SupplierPendingOrders();
			               }
		            	} 
		         }
		    }});
		getContentPane().add(new JScrollPane(pendingOrderTable));

	}

}
