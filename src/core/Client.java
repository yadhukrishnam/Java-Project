package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.Database;

public class Client extends Database {
	public int ClientId;
	public String FirstName, MidName, LastName, Address;
	public String MobileNo;	
	
	public Client()
	{
		
	}
	
	public Client (int ClientId, String FirstName, String MidName, String LastName, String Address, String MobileNo)
	{
		this.ClientId = ClientId;
		this.FirstName = FirstName;
		this.MidName = MidName;
		this.LastName = LastName;
		this.Address = Address;
		this.MobileNo = MobileNo;
	}
	
	public ArrayList<Client> getClients ()
	{
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			
			ResultSet rs = Query("SELECT * FROM Clients LIMIT 100").executeQuery();
			int i = 0;
			while(rs.next())
			{
				clients.add(new Client(rs.getInt("ClientId"), rs.getString("FirstName"), rs.getString("MiddleName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("MobileNo"))); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return clients; 
	}
	
	public ArrayList<Client> searchClient (String keyword)
	{
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			
			PreparedStatement stmt =  Query("SELECT * FROM Clients WHERE FirstName LIKE ? OR MiddleName = ? OR LastName = ? OR Address = ? OR MobileNo = ?");
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			stmt.setString(3, "%" + keyword + "%");
			stmt.setString(4, "%" + keyword + "%");
			stmt.setString(5, "%" + keyword + "%");
			
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				clients.add(new Client(rs.getInt("ClientId"), rs.getString("FirstName"), rs.getString("MiddleName"), rs.getString("LastName"), rs.getString("Address"), rs.getString("MobileNo"))); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return clients; 
	}
	
	public Client getClient(int ClientId)
	{
		try {
			PreparedStatement stmt = Query("SELECT * FROM Clients WHERE ClientId = ?");
			stmt.setLong(1, ClientId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				this.ClientId = ClientId;
				this.FirstName = rs.getString("FirstName");
				this.MidName = rs.getString("MiddleName");
				this.LastName = rs.getString("LastName");
				this.Address = rs.getString("Address");
				this.MobileNo = rs.getString("MobileNo");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return this;
	}
	
	public boolean register()
	{
		try {
			PreparedStatement stmt = Query("INSERT INTO Clients (FirstName, MiddleName, LastName, address, mobileno) VALUES (?, ?, ?, ?, ?)	");
			stmt.setString(1, this.FirstName);
			stmt.setString(2, this.MidName);
			stmt.setString(3, this.LastName);
			stmt.setString(4, this.Address);
			stmt.setString(5, this.MobileNo);
			
			if (stmt.executeUpdate() > 0)
			{
				return true;
			} else {
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		} 
		return false;
	}
	
	public boolean update()
	{
		try {
			PreparedStatement stmt = Query("UPDATE Clients SET FirstName = ?, MiddleName = ?, LastName = ?, address = ?, mobileno = ? WHERE ClientId = ?");
			stmt.setString(1, this.FirstName);
			stmt.setString(2, this.MidName);
			stmt.setString(3, this.LastName);
			stmt.setString(4, this.Address);
			stmt.setString(5, this.MobileNo);
			stmt.setInt(6, this.ClientId);
			
			if (stmt.executeUpdate() > 0)
			{
				return true;
			} else {
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		} 
		return false;
	}
}