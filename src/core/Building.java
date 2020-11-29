package core;

import java.sql.PreparedStatement;
import database.Database;

public class Building extends Database {
	public int BuildingId; 
	public String BuildingName, BuildingType;
	public int ConstructionYear, SiteId, ClientId;
	public long Cost;
	
	public Building(int Bid, String Bname, String BType, int CYear, int SiteId, long Cost , int ClientId)
	{
		this.BuildingId = Bid;
		this.BuildingName = Bname;
		this.BuildingType = BType;
		this.ConstructionYear = CYear;
		this.SiteId = SiteId;
		this.ClientId = ClientId;
		this.Cost = Cost;
	}
	
	public boolean register()
	{
		try {
			PreparedStatement stmt = Query("INSERT INTO Building VALUES (?, ?, ?, ?, ?, ?, ? )");
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
			System.exit(0);
		} 
		return false;
	}
	
}
