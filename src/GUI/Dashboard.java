package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import core.Client;
import core.Site;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

	private JPanel contentPane;

	public Dashboard() {
		try {
			setExtendedState(JFrame.MAXIMIZED_BOTH); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 700, 700);
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mnApplication = new JMenu("Application");
			menuBar.add(mnApplication);
			
			JMenuItem mntmLogout = new JMenuItem("Logout");
			mntmLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					dispose();
				}
			});
			mntmLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					setVisible(false); 
					Login login = new Login();
					login.main("");
					dispose(); 
				}
			});
			mnApplication.add(mntmLogout);
			
			JMenu mnClient = new JMenu("Client");
			menuBar.add(mnClient);
			
			JMenuItem mntmNewClient = new JMenuItem("New Client");
			mntmNewClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FormClient fc = new FormClient();
				}
			});

			mnClient.add(mntmNewClient);
			
			JMenuItem menuItem = new JMenuItem("View Clients");
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ClientList();
				}
			});
			
			JMenuItem mntmSearchClient = new JMenuItem("Search Client");
			mntmSearchClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String keyword =JOptionPane.showInputDialog(null,"Enter Search keyowrd");
					Client cl = new Client();
					ArrayList<Client> clients  = cl.searchClient(keyword);
					  if (clients.size() == 0)
					  {	
						 JOptionPane.showMessageDialog(null, "No search results");
					  } else {
						  ClientList clist = new ClientList();
						  clist.loadTable(clients);  
					  }
				}
			});
			mnClient.add(mntmSearchClient);
			mnClient.add(menuItem);
			
			JMenu MenuSite = new JMenu("Building");
			menuBar.add(MenuSite);
			
			JMenuItem mntmNewBuilding = new JMenuItem("New Building");
			mntmNewBuilding.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new FormBuilding(); 
				}
			});
			MenuSite.add(mntmNewBuilding);
			
			JMenuItem mntmManageBuildings = new JMenuItem("Manage Buildings");
			mntmManageBuildings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BuildingList Bl = new BuildingList(); 
				}
			});
			MenuSite.add(mntmManageBuildings);
			
			JMenu mnSite = new JMenu("Site");
			menuBar.add(mnSite);
			
			JMenuItem mntmNewSite = new JMenuItem("New Site");
			mntmNewSite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new FormSite(); 
				}
			});
			mnSite.add(mntmNewSite);
			
			JMenuItem mntmManageSites = new JMenuItem("Manage Sites");
			mntmManageSites.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SiteList stl = new SiteList();
					stl.loadTable();
				}
			});
			mnSite.add(mntmManageSites);
			
			JMenuItem mntmSearchSite = new JMenuItem("Search Site");
			mntmSearchSite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					  String keyword =JOptionPane.showInputDialog(null,"Enter Search keyowrd");
					  Site st = new Site();
					  ArrayList<Site> sites  = st.searchSite(keyword);
					  if (sites.size() == 0)
					  {	
						 JOptionPane.showMessageDialog(null, "No search results");
					  } else {
						  SiteList stl = new SiteList();
						  stl.loadTable(sites);  
					  }
				}
			});
			mnSite.add(mntmSearchSite);
			
			contentPane = new JPanel();
			contentPane.setBackground(new Color(112, 128, 144));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
		
			this.setTitle("CCMS Dashboard");
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
