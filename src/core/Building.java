package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;

public class Building extends Database {
	public int BuildingId; 
	public String BuildingName, BuildingType, SiteName, SiteLocation, OwnerName, feedback;
	public int ConstructionYear, SiteId, ClientId;
	public long Cost;
	
	public Building() {} 
	
	public Building(int Bid)
	{
		try {
			
			PreparedStatement stmt = Query("SELECT * FROM Building B JOIN Sites S ON S.SiteId = B.SiteId WHERE BuildingId = ? ");
			stmt.setInt(1, Bid); 
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				
				this.BuildingId = rs.getInt("BuildingId");
				this.BuildingName = rs.getString("BuildingName");
				this.BuildingType = rs.getString("Buildingtype");
				this.ConstructionYear = rs.getInt("YearOfConstruction");
				this.ClientId = rs.getInt("ClientId");
				this.Cost =  rs.getLong("Cost");
				this.SiteName = rs.getString("SiteName");
				
				Site s = new Site();
				this.SiteId = s.getSiteId(SiteName);
				
				Client c = new Client(this.ClientId);
				this.OwnerName = c.FirstName + " " + c.LastName + " " + c.MidName; 
				
				this.SiteLocation = s.getSiteLocation(this.SiteName);
				this.feedback = rs.getString("feedback");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
	
	
	public Building(int Bid, String Bname, String BType, int CYear, String SiteName, long Cost , int ClientId, String feedback)
	{
		
		this.BuildingId = Bid;
		this.BuildingName = Bname;
		this.BuildingType = BType;
		this.ConstructionYear = CYear;
		this.ClientId = ClientId;
		this.Cost = Cost;
		this.SiteName = SiteName;
		
		Site s = new Site();
		this.SiteId = s.getSiteId(SiteName);
		
		Client c = new Client(this.ClientId);
		this.OwnerName = c.FirstName + " " + c.LastName + " " + c.MidName; 
		
		this.SiteLocation = s.getSiteLocation(this.SiteName);
		this.feedback = feedback;
	}
	
	public boolean register()
	{
		try {
			PreparedStatement stmt = Query("INSERT INTO Building VALUES (?, ?, ?, ?, ?, ?, ? , '')");
			stmt.setInt(1, this.BuildingId);
			stmt.setString(2, this.BuildingName);
			stmt.setString(3, this.BuildingType);
			stmt.setLong(4, this.ConstructionYear);
			stmt.setLong(5, this.Cost);
			stmt.setInt(6, this.SiteId);
			stmt.setInt(7, this.ClientId);
					
			if (stmt.executeUpdate() > 0)
			{
				return true;
			} else {
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return false;
	}
	
	public ArrayList<Building> getBuildings ()
	{
		ArrayList<Building> buildings = new ArrayList<Building>();
		try {
			
			PreparedStatement stmt = Query("SELECT * FROM Building B JOIN Sites S ON S.SiteId = B.SiteId");
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				 buildings.add(
						 new Building(
								 rs.getInt(1), 
								 rs.getString(2), 
								 rs.getString(3),
								 rs.getInt(4),
								 rs.getString("sitename"),
								 rs.getLong("cost"),
								 rs.getInt("ClientId"),
								 rs.getString("feedback")
				));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return buildings;
	}
	
	public boolean updateDetails()
	{
		try {
			PreparedStatement stmt = Query(
				"UPDATE Building SET BuildingId = ?, BuildingName = ?, "
				+ "BuildingType = ?, yearofconstruction = ?, cost = ?,"
				+ "SiteId = ?, ClientId = ?, feedback = ? WHERE BuildingId = ?"
			);
			stmt.setInt(1, this.BuildingId);
			stmt.setString(2, this.BuildingName);
			stmt.setString(3, this.BuildingType);
			stmt.setLong(4, this.ConstructionYear);
			stmt.setLong(5, this.Cost);
			stmt.setInt(6, this.SiteId);
			stmt.setInt(7, this.ClientId);
			stmt.setString(8, this.feedback);
			stmt.setInt(9, this.BuildingId);
					
			if (stmt.executeUpdate() > 0)
			{
				return true;
			} else {
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return false;
	}
	
	public boolean transferOwnership (int ClientId)
	{
		try {
			PreparedStatement stmt = Query(
				"UPDATE Building SET ClientId = ? WHERE BuildingId = ?"
			);
			
			stmt.setInt(1, ClientId);
			stmt.setInt(2, this.BuildingId);
			
			if (stmt.executeUpdate() > 0)
			{
				return true;
			} else {
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return false;
	}
	
	public ArrayList<Building> getBuildingsOwned (int ClientId){
		ArrayList<Building> buildings = new ArrayList<Building>();
		try {
			
			PreparedStatement stmt = Query("SELECT * FROM Building B JOIN Sites S ON S.SiteId = B.SiteId WHERE B.ClientId = ?");
			stmt.setInt(1, ClientId);
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next())
			{
				 buildings.add(
						 new Building(
								 rs.getInt(1), 
								 rs.getString(2), 
								 rs.getString(3),
								 rs.getInt(4),
								 rs.getString("sitename"),
								 rs.getLong("cost"),
								 rs.getInt("ClientId"),
								 rs.getString("feedback")
				));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return buildings;
	}
	
}
