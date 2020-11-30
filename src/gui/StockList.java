package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.table.TableCellRenderer;

import core.Client;
import core.Site;

public class StockList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public void loadTable(ArrayList<Site> sites)
	{
		for(Site SiteObj :sites)
		{
			Object[] objs = {SiteObj.SiteId, SiteObj.SiteName, SiteObj.SiteLocation};
			tableModel.addRow(objs);
		}
	}
	
	public void loadTable()
	{
		tableModel.setRowCount(0);
		Site st = new Site();
		ArrayList<Site> sites = st.getSites();
		
		for(Site SiteObj :sites)
		{
			Object[] objs = {SiteObj.SiteId, SiteObj.SiteName, SiteObj.SiteLocation};
			tableModel.addRow(objs);
		}
		
	}
	
	public StockList() {
		setTitle("Inventory Stock"); 
		setVisible(true); 
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		String col[] = {"Material ID", "Reorder Level" ,"Quantity Available"};
		tableModel = new DefaultTableModel(col, 0);
		
		table = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		
		 
		contentPane.add(new JScrollPane(table));
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect double click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               
	               FormSite fs = new FormSite();
	               Site currentSite = new Site(Integer.parseInt(table.getValueAt(row, 0).toString()));
	               fs.setData(currentSite);
	               dispose(); 
	            }
	         }
	     });
		this.setResizable(false);
	}
	


}
