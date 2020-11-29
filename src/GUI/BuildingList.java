package GUI;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class BuildingList extends JFrame {
	private JPanel contentPane;
	private JTable table;
	
	public BuildingList() {
		setVisible(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String col[] = {"Building ID","Building Name", "Building Type", "Year Of Construction", "Location", "Cost"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		
		table = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		
		contentPane.add(new JScrollPane(table));
		
		Object[] objs = {1, "Test", "Apartment", 2018, "Ernakulam", 28000};
		tableModel.addRow(objs); 
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect double click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               System.out.println(row); 
	            }
	         }
	     });
	}

}
