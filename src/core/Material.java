package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;
public class Material extends Database{
	public int MaterialID, SupplierId;
	public String MaterialName, SupplierName;
	public int QtyAvailable;
	public int ReOrderLevel;
	
	public Material() {
		
	}
	public Material (int MaterialID)
	{
		try {
			PreparedStatement stmt = Query("SELECT * FROM Materials M JOIN Supplier S ON S.materialid = M.materialid WHERE M.MaterialID = ?");
			stmt.setLong(1, MaterialID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				this.MaterialID = MaterialID;
				this.MaterialName = rs.getString("MaterialName");
				this.QtyAvailable = rs.getInt("quantityavailable");
				this.ReOrderLevel = rs.getInt("ReOrderLevel");
				this.SupplierId = rs.getInt("supplierid");
				this.SupplierName = rs.getString("suppliername"); 
						
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public Material(int MaterialID,String MaterialName, int QtyAvailable, int ReOrderLevel)
	{
		this.MaterialID=MaterialID;
		this.MaterialName=MaterialName;
		this.QtyAvailable=QtyAvailable;
		this.ReOrderLevel=ReOrderLevel;
	}
	public ArrayList<Material> getMaterials ()
	{
		ArrayList<Material> materials = new ArrayList<Material>();
		try {
			
			ResultSet rs = Query("SELECT * FROM Materials").executeQuery();
			while(rs.next())
			{
				materials.add(new Material(rs.getInt("MaterialID"), rs.getString("MaterialName"), rs.getInt("quantityavailable"), rs.getInt("ReOrderLevel"))); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return materials; 
	}
	public ArrayList<Material> searchMaterial (String keyword)
	{
		ArrayList<Material> materials = new ArrayList<Material>();
		try {
			
			PreparedStatement stmt =  Query("SELECT * FROM Materials WHERE MaterialID LIKE ? OR MaterialName = ? OR QtyAvailable = ? OR ReOrderLevel = ?");
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			stmt.setString(3, "%" + keyword + "%");
			stmt.setString(4, "%" + keyword + "%");
			
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				materials.add(new Material(rs.getInt("MaterialID"), rs.getString("MaterialName"), rs.getInt("quantityavailable"), rs.getInt("ReOrderLevel"))); 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return materials; 
	}
	
	public Material getMaterial(int MaterialID)
	{
		try {
			PreparedStatement stmt = Query("SELECT * FROM Materials WHERE MaterialID = ?");
			stmt.setLong(1, MaterialID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				this.MaterialID = rs.getInt("MaterialID");
				this.MaterialName = rs.getString("MaterialName");
				this.QtyAvailable = rs.getInt("quantityavailable");
				this.ReOrderLevel = rs.getInt("ReOrderLevel");
				
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
			PreparedStatement stmt = Query("INSERT INTO Materials VALUES(?,?,?,?)");
			stmt.setInt(1, this.MaterialID);
			stmt.setString(2, this.MaterialName);
			stmt.setInt(3, this.QtyAvailable);
			stmt.setInt(4, this.ReOrderLevel);
			
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
			PreparedStatement stmt = Query("UPDATE Materials SET MaterialID = ?, MaterialName = ?, quantityavailable = ?, ReOrderLevel = ?");
			stmt.setInt(1, this.MaterialID);
			stmt.setString(2, this.MaterialName);
			stmt.setInt(3, this.QtyAvailable);
			stmt.setInt(4, this.ReOrderLevel);
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
   
   public int getSupplier()
   {
	   try {
			PreparedStatement stmt = Query("SELECT SupplierId FROM Supplier WHERE MaterialId = ?");
			stmt.setLong(1, this.MaterialID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				return rs.getInt(1);  
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	   return 0;
   }
}
