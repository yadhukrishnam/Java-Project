package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import core.Client;
import core.Site;
import core.Supplier;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

	private JPanel contentPane;

	public Dashboard() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400, 100, 800, 600);
			setResizable(false);
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
					Login login = new Login();
					dispose(); 
				}
			});
			
			mnApplication.add(mntmLogout);
			
			JMenuItem mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Construction Company Management System V.1.0", "About", 1);
					
				}
			});
			mnApplication.add(mntmAbout);
			
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
			
			JMenuItem mntmChangeOwnership = new JMenuItem("Manage Ownership");
			mntmChangeOwnership.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BuildingList BL = new BuildingList();
					BL.setSelection();
				}
			});
			MenuSite.add(mntmChangeOwnership);
			
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
					  String keyword = JOptionPane.showInputDialog(null,"Enter Search keyoword");
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
			
			JMenu mnMaterials = new JMenu("Materials");
			menuBar.add(mnMaterials);
			
			JMenuItem mntmNewMaterial = new JMenuItem("New Material");
			mntmNewMaterial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new FormMaterial(); 
				}
			});
			mnMaterials.add(mntmNewMaterial);
			
			JMenuItem mntmViewMaterials = new JMenuItem("View Materials");
			mntmViewMaterials.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MaterialList(); 
				}
			});
			mnMaterials.add(mntmViewMaterials);
			
			JMenu mnSupplier = new JMenu("Supplier");
			menuBar.add(mnSupplier);
			
			JMenuItem mntmViewSuppliers = new JMenuItem("View Suppliers");
			mntmViewSuppliers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new SupplierList(); 
				}
			});
			mnSupplier.add(mntmViewSuppliers);
			
			JMenuItem mntmNewSupplier = new JMenuItem("New Supplier");
			mntmNewSupplier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new FormSupplier(); 
				}
			});
			mnSupplier.add(mntmNewSupplier);
			
			JMenuItem mntmSearchSupplier = new JMenuItem("Search Supplier");
			mntmSearchSupplier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String keyword = JOptionPane.showInputDialog(null,"Enter Search keyoword");
					Supplier supplier = new Supplier();
					  ArrayList<Supplier> suppliers  = supplier.searchSupplier(keyword);
					  if (suppliers.size() == 0)
					  {	
						  	JOptionPane.showMessageDialog(null, "No search results");
					  } else {
						  	SupplierList stl = new SupplierList();
							stl.loadTable(suppliers);  
					  }  
				}
			});
			mnSupplier.add(mntmSearchSupplier);
			
			JMenu mnInventory = new JMenu("Inventory");
			menuBar.add(mnInventory);
			
			JMenuItem mntmViewStock = new JMenuItem("View Stock");
			mntmViewStock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			mnInventory.add(mntmViewStock);
			
			contentPane = new JPanel();
			contentPane.setBackground(new Color(102, 153, 255));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setLocationRelativeTo(null);
			
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/backgroud.jpeg")))));
			this.setTitle("Dashboard - Construction Management System ");
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
