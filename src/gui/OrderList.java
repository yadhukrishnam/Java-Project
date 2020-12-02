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
	
	
	public void loadTable()
	{
		tableModel.setRowCount(0);
		Order order = new Order(); 
		ArrayList<Order> Orders = order.getPendingOrders();
		
		for(Order OrderObj : Orders)
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate = dateFormat.format(OrderObj.OrderDate);  
			
			Material m = new Material(OrderObj.MaterialId); 
			Object[] objs = {OrderObj.OrderId, OrderObj.MaterialId, m.MaterialName , OrderObj.toId, m.SupplierName, strDate};
			
			tableModel.addRow(objs);
		}
		
	}
	
	public OrderList() {
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		setLocationRelativeTo(null);
		String col[] = {"Order ID", "Material ID" , "Material Name", "Supplier ID" , "Supplier Name", "Order Date"};
		tableModel = new DefaultTableModel(col, 0);
		
		pendingOrderTable = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		loadTable();
		pendingOrderTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {
	            	Object[] options = { "Delete Order", "Mark as Recived"};

	            	JTable target = (JTable)me.getSource();
	            	int row = target.getSelectedRow(); 
	            	JPanel panel = new JPanel();
	            	panel.add(new JLabel("Select an option that you wish to perform: "));
	               
	            	int result = JOptionPane.showOptionDialog(null, panel, "Enter a Number",
	                       JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
	                       null, options, null);
	               
	            	Order order = new Order(Integer.parseInt(pendingOrderTable.getValueAt(row, 0).toString()));
	            	if (result == JOptionPane.YES_OPTION){
	            	   if (order.deleteOrder() ) {
	            		   JOptionPane.showMessageDialog(null, "Order deleted.");
	            	   } else {
	            		   JOptionPane.showMessageDialog(null, "Could not delete order.");
	            	   }
	            	   
	               } else if (result == JOptionPane.NO_OPTION) {
	            	   if (order.TransactOrder())
	            	   {
	            		   order.deleteOrder();
	            		   JOptionPane.showMessageDialog(null, "Transaction success.");
	            	   } else {
	            		   JOptionPane.showMessageDialog(null, "Could not delete order.");
	            	   }
	               }
	         }
	    }});
	    
		getContentPane().add(new JScrollPane(pendingOrderTable));

	}

}
