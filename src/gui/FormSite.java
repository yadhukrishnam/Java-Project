package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import core.Site;
import core.Site;

public class FormSite extends JFrame {

	private JPanel contentPane;
	private JTextField txtSiteId;
	private JTextField txtSiteName;
	private JTextField txtSiteLocation;
	public boolean isUpdate = false;
	/**
	 * Create the frame.
	 */
	public void setData(Site S)
	{
		this.isUpdate = true; 
		txtSiteId.setText(String.valueOf(S.SiteId));
		txtSiteName.setText(S.SiteName);
		txtSiteLocation.setText(S.SiteLocation);
		this.setTitle("Edit Site");
		txtSiteId.setEditable(false);

	}
	
	public FormSite() {
		this.setVisible(true);
		this.setTitle("New Site");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("Site ID");
		lblNewLabel.setBounds(54, 60, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Site Name");
		lblNewLabel_1.setBounds(54, 99, 77, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Location");
		lblNewLabel_2.setBounds(54, 134, 108, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(":");
		lblNewLabel_3.setBounds(174, 60, 15, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(":");
		lblNewLabel_4.setBounds(174, 99, 15, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(":");
		lblNewLabel_5.setBounds(174, 134, 15, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtSiteId = new JTextField();
		txtSiteId.setBounds(207, 58, 190, 20);
		getContentPane().add(txtSiteId);
		txtSiteId.setColumns(10);
		
		txtSiteName = new JTextField();
		txtSiteName.setBounds(207, 97, 190, 20);
		getContentPane().add(txtSiteName);
		txtSiteName.setColumns(10);
		
		txtSiteLocation = new JTextField();
		txtSiteLocation.setBounds(207, 132, 190, 20);
		getContentPane().add(txtSiteLocation);
		txtSiteLocation.setColumns(10);
		setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Site s = new Site(Integer.parseInt(txtSiteId.getText()), txtSiteName.getText(), txtSiteLocation.getText());
				if (isUpdate) {
					if (s.updateSite())
					{
						JOptionPane.showMessageDialog(null, "Site updated successfully !");
					} else {
						JOptionPane.showMessageDialog(null, "Site ID already exists !");
					}
				} else {
					if (s.newSite()) 
					{
						JOptionPane.showMessageDialog(null, "New site created !");
					} else {
						JOptionPane.showMessageDialog(null, "Site ID already exists !");
					}
				}
			}
		});
		btnNewButton.setBounds(308, 200, 89, 23);
		getContentPane().add(btnNewButton);
	}

}
