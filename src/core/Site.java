package core;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import core.Building;
import database.Database;

public class Site extends Database {
	int SiteId;
	String SiteName, SiteLocation;
	
	public Site(int SiteId, String SiteName, String SiteLocation)
	{
		this.SiteId = SiteId;
		this.SiteName = SiteName;
		this.SiteLocation = SiteLocation;
	}
	
	public boolean newSite()
	{
		try {
			PreparedStatement stmt = Query("INSERT INTO Sites (SiteId, SiteName, SiteLocation) VALUES (?, ?, ?)");
			stmt.setInt(1, this.SiteId);
			stmt.setString(2, this.SiteName);
			stmt.setString(3, this.SiteLocation);
			
			return (stmt.executeUpdate() > 0);  
			
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return false;
	}
	
	
}
