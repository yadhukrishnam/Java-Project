package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import core.Client;
import core.Order;
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
import java.awt.FlowLayout;

public class SupplierDashboard extends JFrame {

	private JPanel contentPane;

	public SupplierDashboard() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400, 100, 800, 600);
			setResizable(false);
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JButton btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose(); 	
				}
			});
			menuBar.add(btnLogout);
			
			JButton btnNewButton = new JButton("New Orders");
			menuBar.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Order History");
			menuBar.add(btnNewButton_1);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("history");
					 SupplierOrderList ol = new SupplierOrderList("FULFILLED");
				}
			});
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("sxrdctfvygbhnj");
					 SupplierOrderList ol = new SupplierOrderList("PENDING");
				}
			});
			
			contentPane = new JPanel();
			contentPane.setBackground(new Color(102, 153, 255));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setLocationRelativeTo(null);
			
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("resources/backgroud.jpeg")))));
			this.setTitle("Dashboard - Supplier Dashboard ");
			getContentPane().setLayout(null);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
