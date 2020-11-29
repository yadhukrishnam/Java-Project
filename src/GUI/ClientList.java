package GUI;

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

import core.Client;

public class ClientList extends JFrame{

	private DefaultTableModel tableModel; 
	private JPanel contentPane;
	private JTable table;
	private Client cl;
	
	public void loadTable()
	{
		tableModel.setRowCount(0);
		cl = new Client();
		ArrayList<Client> clients = cl.getClients();
		
		for(Client ClientObj :clients)
		{
			Object[] objs = {ClientObj.ClientId, ClientObj.FirstName, ClientObj.MidName, ClientObj.LastName, ClientObj.Address, ClientObj.MobileNo};
			tableModel.addRow(objs);
		}
		
	}
	
	public void generateForm()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	public ClientList() {
		generateForm(); 
		
		String col[] = {"Client ID","First Name", "Middle Name", "Last Name", "Address", "Phone No"};
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
	               int row = target.getSelectedRow();
	               
	               FormClient fc = new FormClient();
	               Client currentClient = new Client();
	               fc.setData(currentClient.getClient(Integer.parseInt(table.getValueAt(row, 0).toString())));
	               dispose(); 
	            }
	         }
	     });
		
	}

}
