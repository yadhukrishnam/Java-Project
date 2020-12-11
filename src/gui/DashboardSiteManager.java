package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import core.Material;
import core.Order;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

public class DashboardSiteManager extends JFrame {
	private String Username; 
	private int SiteId; 
	private JPanel contentPane;
	private JTable table;
	private JTable OrdersTable;
	
	public JTable generateTable(String cols[])
	{
		DefaultTableModel tm = new DefaultTableModel(cols, 0);
		return new JTable(tm) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
	}
	
	public void loadPendingOrders(DefaultTableModel tm)
	{
		tm.setRowCount(0);
		ArrayList<Order> orders = new Order().getSitePendingOrders(this.SiteId); 
		for(Order order: orders)
		{
			Material m = new Material(order.MaterialId);
			Object[] objs = {order.OrderId, order.OrderDate, m.MaterialName, order.QuantityOrdered};
			tm.addRow(objs);
		}
	}

	public void loadMaterialsTable(DefaultTableModel tm)
	{
		tm.setRowCount(0);
		ArrayList<Material> materials = new Material().getMaterials();
		for(Material MaterialObj :materials)
		{
			Object[] objs = {MaterialObj.MaterialID, MaterialObj.MaterialName};
			tm.addRow(objs);
		}
	}
	
	public void loadTransactionsTable(DefaultTableModel tm)
	{
		tm.setRowCount(0);
		ArrayList<Order> orders = new Order().getSitePastOrders(SiteId);
		
		for(Order order: orders)
		{
			Material m = new Material(order.MaterialId);
			Object[] objs = {order.OrderId, order.OrderDate,m.MaterialName, order.QuantityOrdered, order.getFulfilledDate() };
			tm.addRow(objs);
		}
	}
	
	public DashboardSiteManager(int SiteId, String Username)  {
		this.Username = Username;
		this.SiteId = SiteId; 
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 800, 600);
		setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		menuBar.add(toolBar);
		
		JButton btnLogout = new JButton("Logout");
		toolBar.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login();
				dispose(); 
			}
		});
		
		JLabel lblWelcome = new JLabel("Welcome, " + this.Username);
		menuBar.add(lblWelcome);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setTitle("Dashboard - Site Manager");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		String OrderTableCols[] = {"Order ID","Order Date", "Material Name", "Quantity Ordered" };
		JTable OrdersTable = generateTable(OrderTableCols);
		
		String MaterialsTableCols[] = {"Material Id", "Material Name"}; 
		JTable MaterialsTable = generateTable(MaterialsTableCols);
		
		String TransactionTableCols[] = {"Order ID","Order Date", "Material Name", "Quantity Ordered", "Transaction Date" };
		JTable TransactionTable = generateTable(TransactionTableCols);
		
		
		loadPendingOrders((DefaultTableModel) OrdersTable.getModel()); 
		loadMaterialsTable((DefaultTableModel) MaterialsTable.getModel());
		loadTransactionsTable((DefaultTableModel) TransactionTable.getModel());
		
		tabbedPane.addTab("Pending Orders", (new JScrollPane(OrdersTable)));
		tabbedPane.addTab("Order History", (new JScrollPane(TransactionTable)));
		tabbedPane.addTab("New Order", (new JScrollPane(MaterialsTable)));
		
		// Place Order for Materials
		MaterialsTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
		            if (me.getClickCount() == 2) {    
		               JTable target = (JTable)me.getSource();
		               int row = target.getSelectedRow();
		               int materialId = Integer.parseInt(MaterialsTable.getValueAt(row, 0).toString()); 
		               try {
		            	   int qty = Integer.parseInt(JOptionPane.showInputDialog("Enter required quantity: "));
		            	   
		            	   if (qty > 0)
		            	   {
		            		   Order o = new Order("OUT", materialId, qty, SiteId );
		            		   if (o.PlaceOrder() == true)
		            		   {
		            			   JOptionPane.showMessageDialog(null, "Order Placed");
		            			   loadPendingOrders((DefaultTableModel) OrdersTable.getModel());
		            		   } else {
		            			   JOptionPane.showMessageDialog(null, "Could not place order.");
		            		   }
		            	   }
		               } 
		               catch (Exception e)
			       		{
			       			JOptionPane.showMessageDialog(null, "Operation cancelled.");
			       		}
		            
		            }
		       	}
		});
		
		getContentPane().add(tabbedPane);
		setVisible(true); 
	}
}
