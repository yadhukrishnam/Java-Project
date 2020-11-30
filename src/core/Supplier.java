package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;

public class Supplier extends Database {
	public int SupplierId,MaterialId;
	public long SupplierContact; 
	public String SupplierName,Address,SupplierMailId ;
	
	public Supplier()
	{
		
	}
	
	public Supplier(int SupplierId, String SupplierName, String Address, int MaterialId,long SupplierContact,String SupplierMailId )
	{
		this.SupplierId =SupplierId ;
		this.SupplierName = SupplierName;
		this.Address = Address;
		this.MaterialId = MaterialId;
		this.SupplierContact=SupplierContact;
		this.SupplierMailId=SupplierMailId;
		
	}
	public ArrayList<Supplier> getSuppliers ()
	{
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			
			ResultSet rs = Query("SELECT * FROM Supplier").executeQuery();
			while(rs.next())
			{
				suppliers.add(new Supplier(rs.getInt("SupplierId"), rs.getString("SupplierName"), rs.getString("Address"), rs.getInt("MaterialId"), rs.getLong("SupplierContact"), rs.getString("SupplierMailId"))); 
			}
		} catch (Exception e)
		{
			
			e.printStackTrace();
			System.exit(0);
		}
		return suppliers; 
	}
	
	public ArrayList<Supplier> searchSupplier (String keyword)
	{
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			
			PreparedStatement stmt =  Query("SELECT * FROM Supplier WHERE SupplierName LIKE ? OR address LIKE ? OR suppliermailid = ? ");
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			stmt.setString(3, "%" + keyword + "%");

			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				suppliers.add(new Supplier(rs.getInt("SupplierId"), rs.getString("SupplierName"), rs.getString("Address"), rs.getInt("MaterialId"), rs.getLong("SupplierContact"), rs.getString("SupplierMailId"))); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return suppliers; 
	}
	
	public Supplier getSupplier(int SupplierId)
	{
		try {
			PreparedStatement stmt = Query("SELECT * FROM Supplier WHERE SupplierId = ?");
			stmt.setLong(1, SupplierId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				this.SupplierId = SupplierId;
				this.SupplierName = rs.getString("SupplierName");
				this.Address = rs.getString("Address");
				this.MaterialId = rs.getInt("MaterialId");
				this.SupplierContact = rs.getLong("SupplierContact");
				this.SupplierMailId = rs.getString("SupplierMailId");
			
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
			PreparedStatement stmt = Query("INSERT INTO Supplier VALUES (?, ?, ?, ?, ?, ? )");
			stmt.setInt(1, this.SupplierId);
			stmt.setString(2, this.SupplierName);
			stmt.setString(3, this.Address);
			stmt.setInt(4, this.MaterialId);
			stmt.setLong(5, this.SupplierContact);
			stmt.setString(6, this.SupplierMailId);
			
			
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
			PreparedStatement stmt = Query("UPDATE Supplier SET SupplierName = ?, Address= ?, MaterialId = ?, SupplierContact = ?, SupplierMailId = ? WHERE SupplierId = ?");
			
			stmt.setString(1, this.SupplierName);
			stmt.setString(2, this.Address);
			stmt.setInt(3, this.MaterialId);
			stmt.setLong(4, this.SupplierContact);
			stmt.setString(5, this.SupplierMailId);
			stmt.setInt(6,this.SupplierId);
			
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
